package com.hui.readwrite.multidata;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/30
 * @Time:10:45
 */
@Aspect
@Component
public class DataSourceAop {

    public static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Before("execution(* com.hui.readwrite.mapper..*.select*(..)) || execution(* com.hui.readwrite.mapper..*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        logger.info("dataSource切换到：Read");
    }

    @Before("execution(* com.hui.readwrite.mapper..*.insert*(..)) || execution(* com.hui.readwrite.mapper..*.update*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        logger.info("dataSource切换到：write");
    }
}
