package com.xjt.food.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Aspect
public class testAspect {
//    @?Before("execute("xxxx")")
    public String beforeMethod(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName());
        return joinPoint.getArgs().toString();
    }

}
