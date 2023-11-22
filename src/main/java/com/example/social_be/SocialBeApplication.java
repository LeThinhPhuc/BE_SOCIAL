package com.example.social_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SocialBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialBeApplication.class, args);
    }



    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // registry.addMapping("/**").allowedOrigins("http://localhost:3000");
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Cấu hình tại đây cho phép gửi yêu cầu từ origin này
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }

        };
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer{
        @Override
        public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
            configurer.defaultContentType(MediaType.APPLICATION_JSON);
        }
    }

}
