package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.controller.BaseController;
import com.hwtx.pf4j.DefaultPluginManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * @author warning5
 */
@Slf4j
@RestController
@RequestMapping("plugin")
public class PluginController extends BaseController {

    DefaultPluginManager pluginManager;

    public PluginController() {
        try {
            pluginManager = new DefaultPluginManager(Paths.get(getClass().getClassLoader().getResource("plugins").toURI()));
            pluginManager.loadPlugins();
        } catch (URISyntaxException e) {
            log.error("{}", e);
        }
    }

    @GetMapping("start")
    public void start(String moduleName, String version) {

    }
}
