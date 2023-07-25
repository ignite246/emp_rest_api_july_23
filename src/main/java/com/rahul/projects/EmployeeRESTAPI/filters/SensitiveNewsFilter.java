package com.rahul.projects.EmployeeRESTAPI.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class SensitiveNewsFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(SensitiveNewsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("====> Filter Init Params which was set during FilterRegistration :: {}",filterConfig.getInitParameterNames());
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("====> SensitiveNewsFilter :: doFilter() invoked <====");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        logger.info("-------------> Incoming Http Request Details ------------------>");
        logger.info("Request Method :: {}",request.getMethod());
        logger.info("Request URI :: {}",request.getRequestURI());
        logger.info("Request URL :: {}",request.getRequestURL());

        //Not allowing to execute "http://localhost:8080/myproject/test-sensitive/news" api call
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
