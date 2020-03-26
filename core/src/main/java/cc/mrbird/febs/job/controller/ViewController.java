package cc.mrbird.febs.job.controller;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.job.entity.Job;
import cc.mrbird.febs.job.service.IJobService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;

/**
 * @author MrBird
 */
@Controller("jobView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "job")
@RequiredArgsConstructor
public class ViewController {

    private final IJobService jobService;

    @GetMapping("job")
    @RequiresPermissions("job:view")
    public String online() {
        return FebsUtil.view("job/job");
    }

    @GetMapping("job/add")
    @RequiresPermissions("job:add")
    public String jobAdd() {
        return FebsUtil.view("job/jobAdd");
    }

    @GetMapping("job/update/{jobId}")
    @RequiresPermissions("job:update")
    public String jobUpdate(@NotBlank(message = "{required}") @PathVariable Long jobId, Model model) {
        Job job = jobService.findJob(jobId);
        model.addAttribute("job", job);
        return FebsUtil.view("job/jobUpdate");
    }

    @GetMapping("log")
    @RequiresPermissions("job:log:view")
    public String log() {
        return FebsUtil.view("job/jobLog");
    }

}
