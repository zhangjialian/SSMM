package com.zjl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志
 */
@Aspect
@Component
public class SystemLogAspect {

    @Resource
    private HttpServletRequest request;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.zjl.controller..*(..))")
    private void controllerAspect(){}

    /************实现部分************/
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        String uri = request.getRequestURI();
        String method = request.getMethod();

        logger.info("info@doBefore, method={}, uri={}", method, uri);
    }

}
