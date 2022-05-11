package com.demi.hdjassignment.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final HttpServletRequest httpServletRequest;

    @Around("execution(* com.demi.hdjassignment.controller.*.*(..))")
    public Object consoleLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();

        if (log.isDebugEnabled()) {
            log.debug("## START - {} / {}", signature.getDeclaringTypeName(), signature.getName());
            httpServletRequest.getParameterMap()
                    .forEach((key, value) -> log.debug("# REQUEST PARAM - {} = {} #", key, value));

            log.debug("## JOIN POINT PROCEED ##");
        }

        Object result = joinPoint.proceed();

        if (log.isDebugEnabled()) {
            log.debug("# RESULT - {}", result);
            log.debug("## FINISH - {} / {}", signature.getDeclaringTypeName(), signature.getName());
        }

        return result;
    }

}