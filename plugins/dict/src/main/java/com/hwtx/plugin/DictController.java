package com.hwtx.plugin;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warning5
 */
@RequestMapping("/plugin/dict-plugin/dict")
@Slf4j
@RestController
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @GetMapping("list")
    @ControllerEndpoint(exceptionMessage = "获取字典列表失败")
    public FebsResponse list(QueryRequest request) throws FebsException {
        dictService.doIt();
        return new FebsResponse().success();
    }
}
