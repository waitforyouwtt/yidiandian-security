package com.yidiandian.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/10/13 20:20
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.yidiandian.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("time aspect start......");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            log.info("arg is "+arg);
        }
        long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        log.info("time aspect 耗时:"+ (System.currentTimeMillis() - start));
        return object;
    }
}
