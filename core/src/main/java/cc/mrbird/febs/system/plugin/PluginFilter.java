package cc.mrbird.febs.system.plugin;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author warning5
 */
@Slf4j
public class PluginFilter implements Filter {

    private PluginService pluginService;

    public PluginFilter(PluginService pluginService) {
        this.pluginService = pluginService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getServletPath();
        int end = path.indexOf("/", 8);
        String pluginId = path.substring(8, end);
        ClassLoader classLoader = pluginService.getPluginManager().getPluginClassLoader(pluginId);
        log.info("load plugin {} for {}", pluginId, path);
        Thread.currentThread().setContextClassLoader(classLoader);
        ClassLoader oldClassloader = Thread.currentThread().getContextClassLoader();
        chain.doFilter(request, response);
        Thread.currentThread().setContextClassLoader(oldClassloader);
    }
}
