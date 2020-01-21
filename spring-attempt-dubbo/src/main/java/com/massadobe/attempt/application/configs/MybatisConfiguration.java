package com.massadobe.attempt.application.configs;

import com.massadobe.attempt.common.config.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * @ClassName: MybatisConfiguration
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 12:01
 * @Version: 1.0.0
 * @param: * @param null
 */
@Configuration
@AutoConfigureAfter({DBConfiguration.class})
public class MybatisConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

    @Bean(name = "sqlSessionFactory")
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        try {

            SqlSessionFactory session = bean.getObject();
            MapperHelper mapperHelper = new MapperHelper();
            //特殊配置
            Config config = new Config();
            //具体支持的参数看后面的文档
            config.setNotEmpty(true);
            //设置配置
            mapperHelper.setConfig(config);
            // 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
            mapperHelper.registerMapper(Mapper.class);
            //配置完成后，执行下面的操作
            mapperHelper.processConfiguration(session.getConfiguration());
            return session;
        } catch (Exception e) {
            logger.error("MybatisConfiguration SqlSessionFactory ERROR: {}", e);
        }
        return null;
    }

    @Bean(name = "sqlSessionTemplate")
    @Autowired
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public MapperScannerConfigurer scannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
        configurer.setBasePackage("com.lljapan.ll.services.dao");
        configurer.setMarkerInterface(Mapper.class);
        return configurer;
    }

}
