package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warning5
 */
@Slf4j
@RestController
@RequestMapping("plugin")
public class PluginController extends BaseController {

    @GetMapping("start")
    public void start(String moduleName, String version) {

    }
}
