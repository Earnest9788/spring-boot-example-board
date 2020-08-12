package com.example.board.controllers.viewController;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewController implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("pages/home");
        registry.addViewController("/home").setViewName("pages/home");
        registry.addViewController("/login").setViewName("pages/user/login");
        registry.addViewController("/regist").setViewName("pages/user/regist");
    }

}