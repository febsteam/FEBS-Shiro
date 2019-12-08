package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.system.service.I18nService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author MrBird
 */
@Slf4j
@RestController
@RequestMapping("i18n")
public class I18nController extends BaseController {

    @Autowired
    private I18nService i18nService;

    @GetMapping("list")
    @ControllerEndpoint(exceptionMessage = "获取国际化配置失败")
    @RequiresPermissions("i18n:view")
    public FebsResponse list(QueryRequest request) throws FebsException {
        Map<String, Object> dataTable = getDataTable(i18nService.getI18nList(request));
        return new FebsResponse().success().data(dataTable);
    }

    @PostMapping("update")
    @ControllerEndpoint(operation = "修改国际化名称", exceptionMessage = "修改国际化名称失败")
    @RequiresPermissions("i18n:update")
    public FebsResponse update(I18nService.I18nLine i18nLine) throws FebsException {
        if (StringUtils.isEmpty(i18nLine.getKey())) {
            throw new FebsException("国际化key不能为空");
        }
        i18nService.update(i18nLine);
        return new FebsResponse().success();
    }

    @PostMapping("add")
    @ControllerEndpoint(operation = "新增国际化名称", exceptionMessage = "新增国际化名称失败")
    @RequiresPermissions("i18n:add")
    public FebsResponse add(I18nService.I18nLine i18nLine) throws FebsException {
        if (StringUtils.isEmpty(i18nLine.getKey())) {
            throw new FebsException("国际化key不能为空");
        }
        i18nService.add(i18nLine);
        return new FebsResponse().success();
    }

    @GetMapping("/delete/{key}")
    @ControllerEndpoint(operation = "删除国际化名称", exceptionMessage = "删除国际化名称失败")
    @RequiresPermissions("i18n:delete")
    public FebsResponse delete(@PathVariable String key) throws FebsException {
        if (StringUtils.isEmpty(key)) {
            throw new FebsException("国际化key不能为空");
        }
        i18nService.delete(key);
        return new FebsResponse().success();
    }
}
