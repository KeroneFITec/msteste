package com.iubi.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


/**
 * Configuration class for internationalization (i18n) support in the application.
 * Sets up locale resolution, message sources, and locale change interceptor.
 *
 * @author Kerone Jr.
 * @since 2025-11-12
 */
@Configuration
public class InternationalizationConfig implements WebMvcConfigurer {

    /**
     * Default locale for the application, loaded from properties.
     */
    @Value("${app.locale:pt_BR}")
    private String defaultLocale;

    /**
     * Bean for resolving the locale based on session.
     *
     * @return LocaleResolver instance
     */
    @Bean
    public LocaleResolver localeResolver() {
    	final SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    	localeResolver.setDefaultLocale(Locale.forLanguageTag(defaultLocale.replace('_', '-'))); 
        return localeResolver;
    }

    /**
     * Bean for loading internationalized messages.
     *
     * @return MessageSource instance
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:messages");
        source.setDefaultEncoding("UTF-8");
        source.setFallbackToSystemLocale(false);
        return source;
    }

    /**
     * Bean for intercepting locale change requests.
     *
     * @return LocaleChangeInterceptor instance
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * Adds the locale change interceptor to the application's registry.
     *
     * @param registry InterceptorRegistry instance
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}