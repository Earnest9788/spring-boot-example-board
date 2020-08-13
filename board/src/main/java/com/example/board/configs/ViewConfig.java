package com.example.board.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfig implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("pages/user/login");
        registry.addViewController("/regist").setViewName("pages/user/regist");
        registry.addViewController("/write").setViewName("pages/article/write");
    }

}