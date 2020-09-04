package com.springboot.aspect;

import com.springboot.annotation.TargetDataSource;
import com.springboot.config.DBContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceAspect {


    /** logger. */
    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 改变数据源，判断使用注解中的数据源实例名称，根据实例名称从上下文管理器中获取数据源
     * @param joinPoint
     * @param targetDataSource
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.info("选择数据源---" + targetDataSource.value());
        DBContextHolder.setDBType(targetDataSource.value());
    }

    /**
     * 使用完后清理数据源
     * @param joinPoint
     * @param targetDataSource
     */
    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.debug("清除数据源 " + targetDataSource.value() + " !");
        DBContextHolder.clearDBType();
    }
}
