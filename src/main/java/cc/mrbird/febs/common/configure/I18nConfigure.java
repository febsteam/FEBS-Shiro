package cc.mrbird.febs.common.configure;

import cc.mrbird.febs.common.exception.RedisConnectException;
import cc.mrbird.febs.monitor.service.IRedisService;
import cc.mrbird.febs.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static cc.mrbird.febs.common.entity.FebsConstant.I18N_SUFFIX;

/**
 * @author warning5
 */
@Configuration
@Slf4j
public class I18nConfigure implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        return new NativeLocaleResolver();
    }

    @Autowired
    private IRedisService iRedisService;

    protected class NativeLocaleResolver extends AcceptHeaderLocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            Object principal = SecurityUtils.getSubject().getPrincipal();
            String language = null;
            if (principal == null) {
                language = request.getParameter("lang");
            } else {
                User user = (User) principal;
                try {
                    language = iRedisService.get(user.getUsername() + I18N_SUFFIX);
                } catch (RedisConnectException e) {
                    log.error("{}", e);
                }
            }
            Locale locale = super.resolveLocale(request);
            if (!StringUtils.isEmpty(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }
    }
}
