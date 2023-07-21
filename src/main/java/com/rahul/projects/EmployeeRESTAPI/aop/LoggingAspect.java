package com.rahul.projects.EmployeeRESTAPI.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.rahul.projects.EmployeeRESTAPI.controllers.EmployeeRestController.*(..))")
    public void logBeforeMethodExecutionStarts(JoinPoint joinPoint){
        logger.info("STARTS === {} ===",joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.rahul.projects.EmployeeRESTAPI.controllers.EmployeeRestController.*(..))")
    public void logAfterMethodExecutionEnds(JoinPoint joinPoint){
        logger.info("ENDS === {} ===",joinPoint.getSignature().getName());
    }
}
