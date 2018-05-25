package cc.mrbird.job.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.job.domain.Job;
import cc.mrbird.job.service.JobService;

@Controller
public class JobController extends BaseController {

	@Autowired
	private JobService jobService;

	@Log("获取定时任务信息")
	@RequestMapping("job")
	@RequiresPermissions("job:list")
	public String index() {
		return "job/job/job";
	}

	@RequestMapping("job/list")
	@ResponseBody
	public Map<String, Object> jobList(QueryRequest request, Job job) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		List<Job> list = this.jobService.findAllJobs(job);
		PageInfo<Job> pageInfo = new PageInfo<Job>(list);
		return getDataTable(pageInfo);
	}

	@RequestMapping("job/checkCron")
	@ResponseBody
	public boolean checkCron(String cron) {
		try {
			return CronExpression.isValidExpression(cron);
		} catch (Exception e) {
			return false;
		}
	}

	@Log("新增任务 ")
	@RequiresPermissions("job:add")
	@RequestMapping("job/add")
	@ResponseBody
	public ResponseBo addJob(Job job) {
		try {
			this.jobService.addJob(job);
			return ResponseBo.ok("新增任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("新增任务失败，请联系网站管理员！");
		}
	}

	@Log("删除任务")
	@RequiresPermissions("job:delete")
	@RequestMapping("job/delete")
	@ResponseBody
	public ResponseBo deleteJob(String ids) {
		try {
			this.jobService.deleteBatch(ids);
			return ResponseBo.ok("删除任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("删除任务失败，请联系网站管理员！");
		}
	}

	@RequestMapping("job/getJob")
	@ResponseBody
	public ResponseBo getJob(Long jobId) {
		try {
			Job job = this.jobService.findJob(jobId);
			return ResponseBo.ok(job);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取任务信息失败，请联系网站管理员！");
		}
	}

	@Log("修改任务 ")
	@RequiresPermissions("job:update")
	@RequestMapping("job/update")
	@ResponseBody
	public ResponseBo updateJob(Job job) {
		try {
			this.jobService.updateJob(job);
			return ResponseBo.ok("修改任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("修改任务失败，请联系网站管理员！");
		}
	}

	@Log("执行任务")
	@RequiresPermissions("job:run")
	@RequestMapping("job/run")
	@ResponseBody
	public ResponseBo runJob(String jobIds) {
		try {
			this.jobService.run(jobIds);
			return ResponseBo.ok("执行任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("执行任务失败，请联系网站管理员！");
		}
	}

	@Log("暂停任务")
	@RequiresPermissions("job:pause")
	@RequestMapping("job/pause")
	@ResponseBody
	public ResponseBo pauseJob(String jobIds) {
		try {
			this.jobService.pause(jobIds);
			return ResponseBo.ok("暂停任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("暂停任务失败，请联系网站管理员！");
		}
	}

	@Log("恢复任务")
	@RequiresPermissions("job:resume")
	@RequestMapping("job/resume")
	@ResponseBody
	public ResponseBo resumeJob(String jobIds) {
		try {
			this.jobService.resume(jobIds);
			return ResponseBo.ok("恢复任务成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("恢复任务失败，请联系网站管理员！");
		}
	}

	@RequestMapping("job/excel")
	@ResponseBody
	public ResponseBo jobExcel(Job job) {
		try {
			List<Job> list = this.jobService.findAllJobs(job);
			return FileUtils.createExcelByPOIKit("任务表", list, Job.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("job/csv")
	@ResponseBody
	public ResponseBo jobCsv(Job job) {
		try {
			List<Job> list = this.jobService.findAllJobs(job);
			return FileUtils.createCsv("任务表", list, Job.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}
}
