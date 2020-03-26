package cc.mrbird.febs.monitor.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.monitor.entity.LoginLog;
import cc.mrbird.febs.monitor.service.ILoginLogService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author MrBird
 */
@Slf4j
@RestController
@RequestMapping("loginLog")
@RequiredArgsConstructor
public class LoginLogController extends BaseController {

    private final ILoginLogService loginLogService;

    @GetMapping("list")
    @RequiresPermissions("loginlog:view")
    public FebsResponse loginLogList(LoginLog loginLog, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.loginLogService.findLoginLogs(loginLog, request));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("delete/{ids}")
    @RequiresPermissions("loginlog:delete")
    @ControllerEndpoint(exceptionMessage = "删除日志失败")
    public FebsResponse deleteLogss(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] loginLogIds = ids.split(StringPool.COMMA);
        this.loginLogService.deleteLoginLogs(loginLogIds);
        return new FebsResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("loginlog:export")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, LoginLog loginLog, HttpServletResponse response) {
        List<LoginLog> loginLogs = this.loginLogService.findLoginLogs(loginLog, request).getRecords();
        ExcelKit.$Export(LoginLog.class, response).downXlsx(loginLogs, false);
    }
}
