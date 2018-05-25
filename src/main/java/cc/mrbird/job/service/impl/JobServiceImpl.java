package cc.mrbird.job.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.job.dao.JobMapper;
import cc.mrbird.job.domain.Job;
import cc.mrbird.job.service.JobService;
import cc.mrbird.job.util.ScheduleUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("JobService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobServiceImpl extends BaseService<Job> implements JobService {

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private JobMapper jobMapper;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init() {
		List<Job> scheduleJobList = this.jobMapper.queryList();
		for (Job scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
			// 如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			} else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	@Override
	public Job findJob(Long jobId) {
		return this.selectByKey(jobId);
	}

	@Override
	public List<Job> findAllJobs(Job job) {
		try {
			Example example = new Example(Job.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(job.getBeanName())) {
				criteria.andCondition("bean_name=", job.getBeanName());
			}
			if (StringUtils.isNotBlank(job.getMethodName())) {
				criteria.andCondition("method_name=", job.getMethodName());
			}
			if (StringUtils.isNotBlank(job.getStatus())) {
				criteria.andCondition("status=", Long.valueOf(job.getStatus()));
			}
			example.setOrderByClause("job_id");
			return this.selectByExample(example);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return new ArrayList<Job>();
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addJob(Job job) {
		job.setCreateTime(new Date());
		job.setStatus(Job.ScheduleStatus.PAUSE.getValue());
		this.save(job);
		ScheduleUtils.createScheduleJob(scheduler, job);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateJob(Job job) {
		ScheduleUtils.updateScheduleJob(scheduler, job);
		this.updateNotNull(job);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteBatch(String jobIds) {
		List<String> list = Arrays.asList(jobIds.split(","));
		for (String jobId : list) {
			ScheduleUtils.deleteScheduleJob(scheduler, Long.valueOf(jobId));
		}
		this.batchDelete(list, "jobId", Job.class);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int updateBatch(String jobIds, String status) {
		List<String> list = Arrays.asList(jobIds.split(","));
		Example example = new Example(Job.class);
		example.createCriteria().andIn("jobId", list);
		Job job = new Job();
		job.setStatus(status);
		return this.jobMapper.updateByExampleSelective(job, example);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void run(String jobIds) {
		List<String> list = Arrays.asList(jobIds.split(","));
		for (String jobId : list) {
			ScheduleUtils.run(scheduler, this.findJob(Long.valueOf(jobId)));
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void pause(String jobIds) {
		List<String> list = Arrays.asList(jobIds.split(","));
		for (String jobId : list) {
			ScheduleUtils.pauseJob(scheduler, Long.valueOf(jobId));
		}
		this.updateBatch(jobIds, Job.ScheduleStatus.PAUSE.getValue());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void resume(String jobIds) {
		List<String> list = Arrays.asList(jobIds.split(","));
		for (String jobId : list) {
			ScheduleUtils.resumeJob(scheduler, Long.valueOf(jobId));
		}
		this.updateBatch(jobIds, Job.ScheduleStatus.NORMAL.getValue());
	}

}
