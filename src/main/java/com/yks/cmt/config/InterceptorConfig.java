package com.yks.cmt.config;

import com.yks.cmt.common.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/*/login","/*/register","/websocket","/WebSocket.html","/image/**","/**/*.*","/common/*","/report/*","/chatbot/*");
    }

    @Bean
    public JwtUtils jwtInterceptor() {
        return new JwtUtils();
    }
}
