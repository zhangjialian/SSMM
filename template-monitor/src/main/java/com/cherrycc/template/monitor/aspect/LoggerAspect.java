package com.cherrycc.template.monitor.aspect;

/**
 * @author BG349176
 * @date 2018/8/14 16:38
 */

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Before("execution(* com.cherrycc.template.service..*(..))")
    public void beforeLogger(){
        //System.out.println("begin logger");
    }

    @After("execution(* com.cherrycc.template.service..*(..))")
    public void afterLogger(){
        //System.out.println("after logger");
    }

}
