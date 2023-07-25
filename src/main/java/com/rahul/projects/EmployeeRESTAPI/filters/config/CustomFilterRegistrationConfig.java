package com.rahul.projects.EmployeeRESTAPI.filters.config;

import com.rahul.projects.EmployeeRESTAPI.filters.SensitiveNewsFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomFilterRegistrationConfig {

    Logger logger = LoggerFactory.getLogger(CustomFilterRegistrationConfig.class);

    @Bean
    public FilterRegistrationBean<SensitiveNewsFilter> sensitiveNewsFilterFilterRegistration(){
        logger.info("##### sensitiveNewsFilterFilterRegistration registration invoked #####");
        final FilterRegistrationBean<SensitiveNewsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new SensitiveNewsFilter());

        //This filter will be applied only to the request having "http://localhost:8080/myproject/test-sensitive/news" in URL
        //Its being invoked everytime :: Need to resolve it
        filterRegistrationBean.addUrlPatterns("/myproject/test-sensitive/*");
        filterRegistrationBean.setOrder(1);

        Map<String,String> initParamMap = new HashMap<>();
        initParamMap.put("testKey1","testValue1");
        filterRegistrationBean.setInitParameters(initParamMap);

        logger.info("#### sensitiveNewsFilterFilterRegistration bean :: {}",filterRegistrationBean.toString());
        return filterRegistrationBean;
    }
}
