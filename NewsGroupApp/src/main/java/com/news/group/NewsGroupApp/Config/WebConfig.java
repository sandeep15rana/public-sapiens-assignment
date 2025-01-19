package com.news.group.NewsGroupApp.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from any origin (can be restrictive, but for development purposes)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Allow frontend app from localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow necessary HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true);
    }
}
