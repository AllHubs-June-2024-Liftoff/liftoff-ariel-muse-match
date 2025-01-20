package com.gw.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.CacheControl.maxAge;

@Configuration
public class WebConfig {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("api/**")
//                .allowedOrigins("http://localhost:5173")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }


    //Allows all traffic from 5173 (GET POST PUT DELETE OPTIONS)

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Methods", "Access-Control-Allow-Headers")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
