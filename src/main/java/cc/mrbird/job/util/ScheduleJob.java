package cc.mrbird.job.util;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cc.mrbird.common.util.SpringContextUtils;
import cc.mrbird.job.domain.Job;
import cc.mrbird.job.domain.JobLog;
import cc.mrbird.job.service.JobLogService;

/**
 * 定时任务
 * 
 * @author Administrator
 *
 */
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ExecutorService service = Executors.newSingleThreadExecutor();

	@Override
	protected void executeInternal(JobExecutionContext context) {
		Job scheduleJob = (Job) context.getMergedJobDataMap().get(Job.JOB_PARAM_KEY);

		// 获取spring bean
		JobLogService scheduleJobLogService = (JobLogService) SpringContextUtils.getBean("JobLogService");

		JobLog log = new JobLog();
		log.setJobId(scheduleJob.getJobId());
		log.setBeanName(scheduleJob.getBeanName());
		log.setMethodName(scheduleJob.getMethodName());
		log.setParams(scheduleJob.getParams());
		log.setCreateTime(new Date());

		long startTime = System.currentTimeMillis();

		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
			ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
					scheduleJob.getParams());
			Future<?> future = service.submit(task);
			future.get();
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(times);
			// 任务状态 0：成功 1：失败
			log.setStatus("0");

			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
			long times = System.currentTimeMillis() - startTime;
			log.setTimes(times);
			// 任务状态 0：成功 1：失败
			log.setStatus("1");
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		} finally {
			scheduleJobLogService.saveJobLog(log);
		}
	}
}
