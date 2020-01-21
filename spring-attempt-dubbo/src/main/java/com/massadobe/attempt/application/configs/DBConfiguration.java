package com.massadobe.attempt.application.configs;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.massadobe.attempt.common.config.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: DBConfiguration
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:58
 * @Version: 1.0.0
 * @param: * @param null
 */
@Configuration
@EnableTransactionManagement
public class DBConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(DBConfiguration.class);

    private final static String DATASOURCE_FILTER = "stat,wall";
    private final static String VALID_QUERY = "SELECT 1 FROM dual";

    @NacosValue(value = "${master.username}")
    private String m_username;
    @NacosValue("${master.password}")
    private String m_password;
    @NacosValue("${master.url}")
    private String m_url;
    @NacosValue("${master.driver-class-name}")
    private String m_drivername;
    @NacosValue("${master.max-active}")
    private int m_max_active;
    @NacosValue("${master.min-idle}")
    private int m_min_idle;
    @NacosValue("${master.login-timeout}")
    private int m_login_timeout;
    @NacosValue("${master.initial-size}")
    private int m_initial_size;
    @NacosValue("${master.max-wait}")
    private int m_max_wait;
    @NacosValue("${master.time-between-eviction-runs-millis}")
    private int m_time_between;
    @NacosValue("${master.min-evictable-idle-time-millis}")
    private int m_min_evictable;

    @NacosValue("${slave.username}")
    private String s_username;
    @NacosValue("${slave.password}")
    private String s_password;
    @NacosValue("${slave.url}")
    private String s_url;
    @NacosValue("${slave.driver-class-name}")
    private String s_drivername;
    @NacosValue("${slave.max-active}")
    private int s_max_active;
    @NacosValue("${slave.min-idle}")
    private int s_min_idle;
    @NacosValue("${slave.login-timeout}")
    private int s_login_timeout;
    @NacosValue("${slave.initial-size}")
    private int s_initial_size;
    @NacosValue("${slave.max-wait}")
    private int s_max_wait;
    @NacosValue("${slave.time-between-eviction-runs-millis}")
    private int s_time_between;
    @NacosValue("${slave.time-between-eviction-runs-millis}")
    private int s_min_evictable;

    @SuppressWarnings("all")
    @Bean(name = "masterDataSource")
    public DataSource master() {
        System.out.println("get into master");
        DruidDataSource ds = new DruidDataSource();
        ds.setUsername(m_username);
        ds.setPassword(m_password);
        ds.setUrl(m_url);
        ds.setDriverClassName(m_drivername);
        ds.setValidationQuery(VALID_QUERY);
        ds.setMaxActive(m_max_active);
        ds.setMinIdle(m_min_idle);
        ds.setMinEvictableIdleTimeMillis(m_min_evictable);
        ds.setLoginTimeout(m_login_timeout);
        ds.setInitialSize(m_initial_size);
        ds.setMaxWait(m_max_wait);
        ds.setTimeBetweenEvictionRunsMillis(m_time_between);
        try {
            ds.setFilters(DATASOURCE_FILTER);
        } catch (SQLException e) {
            logger.error("DBConfiguration MASTER ERROR: {}", e);
        }
        return ds;
    }

    @SuppressWarnings("all")
    @Bean(name = "slaveDataSource")
    public DataSource slave() {
        System.out.println("get into slave");
        DruidDataSource ds = new DruidDataSource();
        ds.setUsername(s_username);
        ds.setPassword(s_password);
        ds.setUrl(s_url);
        ds.setDriverClassName(s_drivername);
        ds.setValidationQuery(VALID_QUERY);
        ds.setMaxActive(s_max_active);
        ds.setMinIdle(s_min_idle);
        ds.setMinEvictableIdleTimeMillis(s_min_evictable);
        ds.setLoginTimeout(s_login_timeout);
        ds.setInitialSize(s_initial_size);
        ds.setMaxWait(s_max_wait);
        ds.setTimeBetweenEvictionRunsMillis(s_time_between);
        try {
            ds.setFilters(DATASOURCE_FILTER);
        } catch (SQLException e) {
            logger.error("DBConfiguration SLAVE ERROR: {}", e);
        }
        return ds;
    }

    /**
     * 把多数据设置在dynamicDataSource，默认读取master数据库
     */
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSource.DatabaseType.Read, masterDataSource);
        targetDataSources.put(DynamicDataSource.DatabaseType.Write, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        return dataSource;
    }

}
