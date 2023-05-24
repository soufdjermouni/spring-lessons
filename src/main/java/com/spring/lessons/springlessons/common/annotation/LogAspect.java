package com.spring.lessons.springlessons.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("@annotation(Log)")
    public Object log(ProceedingJoinPoint joinPoint ) throws Throwable {
        String name = (String) joinPoint.getArgs()[0];
        System.out.println(String.format("Log: hello %s,", name));
        return joinPoint.proceed();
    }
}
