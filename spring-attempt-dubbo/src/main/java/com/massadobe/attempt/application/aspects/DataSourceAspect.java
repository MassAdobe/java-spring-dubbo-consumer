package com.massadobe.attempt.application.aspects;

import com.massadobe.attempt.common.config.DynamicDataSource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceAspect
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:48
 * @Version: 1.0.0
 * @param: * @param null
 */
@Aspect
@Component
public class DataSourceAspect {

    private final static Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("@annotation(com.massadobe.attempt.common.annotation.Read)&& !@annotation(com.massadobe.attempt.common.annotation.Write)")
    public void setWriteDataSourceType() {
        DynamicDataSource.master();
        logger.info("dataSource切换到：Read");
    }

    @Before("@annotation(com.massadobe.attempt.common.annotation.Write) && !@annotation(com.massadobe.attempt.common.annotation.Read)")
    public void setReadDataSourceType() {
        DynamicDataSource.slave();
        logger.info("dataSource切换到：Write");
    }

}
