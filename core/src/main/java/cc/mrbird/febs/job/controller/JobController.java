package cc.mrbird.febs.job.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.job.entity.Job;
import cc.mrbird.febs.job.service.IJobService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronExpression;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@Slf4j
@Validated
@RestController
@RequestMapping("job")
@RequiredArgsConstructor
public class JobController extends BaseController {

    private final IJobService jobService;

    @GetMapping
    @RequiresPermissions("job:view")
    public FebsResponse jobList(QueryRequest request, Job job) {
        Map<String, Object> dataTable = getDataTable(this.jobService.findJobs(request, job));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("cron/check")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    @RequiresPermissions("job:add")
    @ControllerEndpoint(operation = "新增定时任务", exceptionMessage = "新增定时任务失败")
    public FebsResponse addJob(@Valid Job job) {
        this.jobService.createJob(job);
        return new FebsResponse().success();
    }

    @GetMapping("delete/{jobIds}")
    @RequiresPermissions("job:delete")
    @ControllerEndpoint(operation = "删除定时任务", exceptionMessage = "删除定时任务失败")
    public FebsResponse deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringPool.COMMA);
        this.jobService.deleteJobs(ids);
        return new FebsResponse().success();
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改定时任务", exceptionMessage = "修改定时任务失败")
    public FebsResponse updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
        return new FebsResponse().success();
    }

    @GetMapping("run/{jobIds}")
    @RequiresPermissions("job:run")
    @ControllerEndpoint(operation = "执行定时任务", exceptionMessage = "执行定时任务失败")
    public FebsResponse runJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.run(jobIds);
        return new FebsResponse().success();
    }

    @GetMapping("pause/{jobIds}")
    @RequiresPermissions("job:pause")
    @ControllerEndpoint(operation = "暂停定时任务", exceptionMessage = "暂停定时任务失败")
    public FebsResponse pauseJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
        return new FebsResponse().success();
    }

    @GetMapping("resume/{jobIds}")
    @RequiresPermissions("job:resume")
    @ControllerEndpoint(operation = "恢复定时任务", exceptionMessage = "恢复定时任务失败")
    public FebsResponse resumeJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.resume(jobIds);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("job:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, Job job, HttpServletResponse response) {
        List<Job> jobs = this.jobService.findJobs(request, job).getRecords();
        ExcelKit.$Export(Job.class, response).downXlsx(jobs, false);
    }
}
