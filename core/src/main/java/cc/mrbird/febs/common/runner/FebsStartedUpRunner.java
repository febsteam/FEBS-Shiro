package cc.mrbird.febs.common.runner;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.properties.FebsProperties;
import cc.mrbird.febs.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author MrBird
 * @author FiseTch
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FebsStartedUpRunner implements ApplicationRunner {

    private final ConfigurableApplicationContext context;
    private final FebsProperties febsProperties;
    private final RedisService redisService;

    @Value("${server.port:8080}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            // 测试 Redis连接是否正常
            redisService.hasKey("febs_test");
        } catch (Exception e) {
            log.error(" ____   __    _   _ ");
            log.error("| |_   / /\\  | | | |");
            log.error("|_|   /_/--\\ |_| |_|__");
            log.error("                        ");
            log.error("FEBS启动失败，{}", e.getMessage());
            log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            // 关闭 FEBS
            context.close();
        }
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = febsProperties.getShiro().getLoginUrl();
            if (StringUtils.isNotBlank(contextPath)) {
                url += contextPath;
            }
            if (StringUtils.isNotBlank(loginUrl)) {
                url += loginUrl;
            }
            log.info(" __    ___   _      ___   _     ____ _____  ____ ");
            log.info("/ /`  / / \\ | |\\/| | |_) | |   | |_   | |  | |_  ");
            log.info("\\_\\_, \\_\\_/ |_|  | |_|   |_|__ |_|__  |_|  |_|__ ");
            log.info("                                                      ");
            log.info("FEBS 权限系统启动完毕，地址：{}", url);

            boolean auto = febsProperties.isAutoOpenBrowser();
            if (auto && StringUtils.equalsIgnoreCase(active, FebsConstant.DEVELOP)) {
                String os = System.getProperty("os.name");
                // 默认为 windows时才自动打开页面
                if (StringUtils.containsIgnoreCase(os, FebsConstant.SYSTEM_WINDOWS)) {
                    //使用默认浏览器打开系统登录页
                    Runtime.getRuntime().exec("cmd  /c  start " + url);
                }
            }
        }
    }
}
