package cc.mrbird.febs.common.configure;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author warning5
 */
@Configuration
public class I18nConfigure implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new NativeLocaleResolver();
    }

    protected static class NativeLocaleResolver extends AcceptHeaderLocaleResolver {
        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
            setDefaultLocale(locale);
        }

        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter("lang");
            Locale locale = getDefaultLocale();
            if (!StringUtils.isEmpty(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }
    }
}
