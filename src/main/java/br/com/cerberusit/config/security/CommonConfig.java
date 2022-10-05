package br.com.cerberusit.config.security;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonConfig {

    public static final String SECRET_PHRASE = "c3rb3rus1t";

    public static final String[] IGNORE_MATCHERS = {
            "/swagger-resources/**",
            "/swagger-resources/configuration/**",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/v3/api-docs",
            "/webjars/**",
            "/actuator/**",
            "/auth/forgot-password/**",
            "/dir/**",
            "/public/**",
            "/swagger-ui/**",
            "/index.html",
    };

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(2000000);
        return multipartResolver;
    }
}