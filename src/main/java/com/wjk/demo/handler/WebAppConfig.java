package com.wjk.demo.handler;


import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration

public class WebAppConfig extends WebMvcConfigurerAdapter {
    @SuppressAjWarnings
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/path/**").addResourceLocations("file:C:\\Users\\Administrator\\Desktop\\images/");
        super.addResourceHandlers(registry);
    }
}
