package com.example.blissmap.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspects {
    @Around("execution(* com.example.blissmap.Services.*.*(..))")
    public Object aroundServiceAdvice(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Service method " + jp.getSignature().getName() + " has been called with arguments : " +  Arrays.toString(jp.getArgs()));
        return jp.proceed();
    }

    @Around("execution(* com.example.blissmap.Controllers.*.*(..))")
    public Object aroundControllerAdvice(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("Controller method " + jp.getSignature().getName() + " has been called.");
        Object shownPage = jp.proceed();
        System.out.println("Which is now showing the page : " + shownPage);
        return shownPage;
    }

    /** @After("execution(* com.example.blissmap.Services.TomTomService.searchSpas(..))")
    public void afterControllerAdvice() {
        System.out.println("Service method searchSpas has successfully been called.");
    } **/
    

}