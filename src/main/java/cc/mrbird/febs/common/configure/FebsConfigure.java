package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.properties.FebsProperties;
import cc.mrbird.febs.common.properties.SwaggerProperties;
import cc.mrbird.febs.common.xss.XssFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author MrBird
 */
@Configuration
@EnableSwagger2
public class FebsConfigure {

    @Autowired
    private FebsProperties properties;
    @Autowired
    private ApplicationContext applicationContext;


    @Component
    public class FebsWebMvcRegistrations implements WebMvcRegistrations {

        @Override
        public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
            return new ModuleRequestMappingHandlerMapping();
        }
    }

    class ModuleRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        public void addControllerMapping(String moduleName, Class<?> controllerClass) {
            if (controllerClass != null && isHandler(controllerClass)) {
                DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(controllerClass);
                defaultListableBeanFactory.registerBeanDefinition(moduleName + "_" + controllerClass.getSimpleName(), beanDefinitionBuilder.getBeanDefinition());
                detectHandlerMethods(controllerClass);
            } else {
                throw new RuntimeException("Controller:" + controllerClass + " in module:" + moduleName + " is invalidate");
            }
        }

        public void removeMapping(String moduleName, Class<?> controllerClass) {
            Object controller = applicationContext.getBean(controllerClass);
            if (controller == null) {
                System.out.println("spring容器中已不存在该实体");
            }
            ReflectionUtils.doWithMethods(controllerClass, method -> {
                Method specificMethod = ClassUtils.getMostSpecificMethod(method, controllerClass);
                try {
                    RequestMappingInfo requestMappingInfo = getMappingForMethod(specificMethod, controllerClass);
                    if (requestMappingInfo != null) {
                        unregisterMapping(requestMappingInfo);
                    }
                } catch (Exception e) {
                    logger.error("unregisterMapping " + controllerClass + ",in module " + moduleName + " failure.", e);
                }
            }, ReflectionUtils.USER_DECLARED_METHODS);
        }
    }


    @Bean(FebsConstant.ASYNC_POOL)
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("Febs-Async-Thread");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * XssFilter Bean
     */
    @Bean
    public FilterRegistrationBean<XssFilter> xssFilterRegistrationBean() {
        FilterRegistrationBean<XssFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

    @Bean
    public Docket swaggerApi() {
        SwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger));
    }

    private ApiInfo apiInfo(SwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

}
