package com.starfish.manager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InsertAspect {

    @Pointcut("execution(* com.zjl.manager..insert*(..))")
    private void insert(){}

    @Before("insert()")
    public void beforeInsert(JoinPoint joinPoint){
        System.out.println("--insert action begin--");
    }

    @After("insert()")
    public void afterInsert(JoinPoint joinPoint){
        System.out.println("--insert action after--");
    }
}
