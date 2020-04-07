package cc.mrbird.febs.system.plugin;

import cc.mrbird.febs.common.configure.FebsConfigure;
import com.google.common.collect.Lists;
import com.hwtx.pf4j.DefaultPluginManager;
import com.hwtx.pf4j.PluginState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

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
            List<Class<?>> controllerClasses = Lists.newArrayList();
            if (pluginState.equals(PluginState.STARTED) || pluginState.equals(PluginState.CREATED)) {
                invokeFileAction(pluginPath, classLoader,
                        (clazz, beanFactory) -> {
                            if (pluginRequestMappingHandlerMapping.isHandler(clazz)) {
                                controllerClasses.add(clazz);
                            }
                            if (clazz.isAnnotationPresent(Service.class)) {
                                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                                GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();

                                //注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
                                // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
                                // 其返回的是该工厂Bean的getObject方法所返回的对象。
                                definition.setBeanClass(clazz);

                                //这里采用的是byType方式注入，类似的还有byName等
                                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
                                beanFactory.registerBeanDefinition(clazz.getSimpleName(), definition);
                            }
                        });
                log.info("plugin " + pluginId + " loaded.");
            } else if (pluginState.equals(PluginState.STOPPED)) {
                invokeFileAction(pluginPath, classLoader,
                        (clazz, beanFactory) -> pluginRequestMappingHandlerMapping.removeMapping(pluginId, clazz));
                log.info("plugin " + pluginId + " stopped.");
            }
            controllerClasses.forEach(clazz -> pluginRequestMappingHandlerMapping.addControllerMapping(pluginId, clazz));
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
                            callback.invoke(clazz, beanFactory);
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
        void invoke(Class<?> clazz, DefaultListableBeanFactory beanFactory);
    }
}
