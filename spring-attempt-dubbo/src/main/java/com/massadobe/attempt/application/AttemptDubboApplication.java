package com.massadobe.attempt.application;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.massadobe.attempt.common.config.OSinfo;
import com.massadobe.attempt.common.constants.ConstantsConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.util.Properties;


/**
 * @ClassName: AttemptApplication
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 15:09
 * @Version: 1.0.0
 * @param: * @param null
 */
@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
@MapperScan(basePackages = {ConstantsConfig.TK_MYBATIS_SCAN_ADDR})
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = ConstantsConfig.NACOS_ADDRS, namespace = ConstantsConfig.NACOS_NAMESPACE))
@NacosPropertySource(dataId = ConstantsConfig.NACOS_FILE_NAME, groupId = ConstantsConfig.NACOS_GROUP, autoRefreshed = ConstantsConfig.NACOS_REFRESH, type = ConfigType.YAML)
public class AttemptDubboApplication {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        if (OSinfo.isWindows()) {
            properties.setProperty(ConstantsConfig.LOG_CONFIG_LOCATION_NAME, ConstantsConfig.WIN_LOG_PATH);
        } else if (OSinfo.isMacOSX() || OSinfo.isMacOS()) {
            properties.setProperty(ConstantsConfig.LOG_CONFIG_LOCATION_NAME, ConstantsConfig.MAC_LOG_PATH);
        } else {
            properties.setProperty(ConstantsConfig.LOG_CONFIG_LOCATION_NAME, ConstantsConfig.LINUX_LOG_PATH);
        }
        SpringApplication app = new SpringApplication(AttemptDubboApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }
}
