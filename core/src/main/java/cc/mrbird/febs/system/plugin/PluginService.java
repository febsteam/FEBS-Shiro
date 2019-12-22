package cc.mrbird.febs.system.plugin;

import cc.mrbird.febs.common.configure.FebsConfigure;
import com.hwtx.pf4j.DefaultPluginManager;
import com.hwtx.pf4j.PluginState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author warning5
 */
@Service
@Slf4j
public class PluginService {

    @Autowired
    private ApplicationContext applicationContext;
    @Getter
    DefaultPluginManager pluginManager;
    FebsConfigure.PluginRequestMappingHandlerMapping pluginRequestMappingHandlerMapping;

    @PostConstruct
    public void init() {
        pluginRequestMappingHandlerMapping = (FebsConfigure.PluginRequestMappingHandlerMapping) applicationContext.getBean(RequestMappingHandlerMapping.class);
        try {
            pluginManager = new DefaultPluginManager(Paths.get(PluginService.class.getClassLoader().getResource("plugins").toURI()));
            pluginManager.loadPlugins();
            pluginManager.addPluginStateListener(event -> {
                load(event.getPlugin().getPluginId(), event.getPlugin().getPluginClassLoader(),
                        event.getPluginState(), new File(event.getPlugin().getPluginPath().toFile(), "classes").toPath());
            });
            pluginManager.startPlugins();
        } catch (URISyntaxException e) {
            log.error("{}", e);
        }
    }

    private void load(String pluginId, ClassLoader classLoader, PluginState pluginState, Path pluginPath) {
        try {
            if (pluginState.equals(PluginState.STARTED) || pluginState.equals(PluginState.CREATED)) {
                invokeFileAction(pluginPath, classLoader,
                        clazz -> pluginRequestMappingHandlerMapping.addControllerMapping(pluginId, clazz));
                log.info("plugin " + pluginId + " loaded.");
            } else if (pluginState.equals(PluginState.STOPPED)) {
                invokeFileAction(pluginPath, classLoader,
                        clazz -> pluginRequestMappingHandlerMapping.removeMapping(pluginId, clazz));
                log.info("plugin " + pluginId + " stopped.");
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeFileAction(Path pluginPath, ClassLoader classLoader, Callback callback) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator = null;
        try {
            annotationAwareAspectJAutoProxyCreator = beanFactory.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        } catch (Exception e) {

        }
        try {
            AnnotationAwareAspectJAutoProxyCreator _annotationAwareAspectJAutoProxyCreator = annotationAwareAspectJAutoProxyCreator;
            Files.walkFileTree(pluginPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String filePath = file.toFile().getAbsolutePath();
                    if (filePath.endsWith(".class")) {
                        String tem = file.toFile().getPath().replaceAll("\\\\", "/");
                        String classname = tem.substring(tem.lastIndexOf("/classes") + "/classes".length() + 1, tem.indexOf(".class"));
                        try {
                            Class<?> clazz = classLoader.loadClass(classname.replaceAll("/", "."));
                            if (_annotationAwareAspectJAutoProxyCreator != null) {
                                _annotationAwareAspectJAutoProxyCreator.setProxyClassLoader(classLoader);
                            }
                            callback.invoke(clazz);
                        } catch (ClassNotFoundException e) {
                            log.error("{}", e);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            log.error("{}", e);
        }
    }

    @FunctionalInterface
    interface Callback {
        void invoke(Class<?> clazz);
    }
}
