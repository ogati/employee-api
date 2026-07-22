package com.example.employee.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.UrlHandlerFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Bean
    public UrlHandlerFilter trailingSlashFilter() {
        // intercept requests so that URLs with and without a trailing slash are treated as identical
        return UrlHandlerFilter.trailingSlashHandler("/employees/**")
                .wrapRequest()
                .build();
    }
}
