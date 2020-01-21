package com.massadobe.attempt.application.configs;

import com.massadobe.attempt.common.config.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: TransactionConfiguration
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 12:03
 * @Version: 1.0.0
 * @param: * @param null
 */
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({MybatisConfiguration.class})
public class TransactionConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(TransactionConfiguration.class);

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        logger.info("事物配置");
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
