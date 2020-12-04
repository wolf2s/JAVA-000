package com.example.demo.Aspect;

import com.example.demo.config.DataSourceRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceSqlAspect {

    @Pointcut(value = "execution(* com.example.demo.mapper.*.*(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        if (name.startsWith("save") || name.startsWith("insert")) {
            log.info("执行了master");
            DataSourceRoutingDataSource.MASTER_STATUS.set(true);
        } else {
            log.info("执行了slave");
            DataSourceRoutingDataSource.MASTER_STATUS.set(false);
        }
    }

}
