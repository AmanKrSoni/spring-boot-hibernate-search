package com.aman.springbootfts.config;

import com.aman.springbootfts.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan(basePackages = {"com.aman.springbootfts"})
public class CoreConfig implements WebMvcConfigurer {
	
   
    @Autowired
    private Interceptor interceptor;


    /**
     * @param registry add InterceptorRegistry
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
