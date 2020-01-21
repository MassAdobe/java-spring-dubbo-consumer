package com.massadobe.attempt.common.constants;

/**
 * @ClassName: ConstantsConfig
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 12:04
 * @Version: 1.0.0
 * @param: * @param null
 */
public class ConstantsConfig {

    /**
     * log的配置名称
     */
    public final static String LOG_CONFIG_LOCATION_NAME = "logging.fileLocation";
    /**
     * windows系统log日志存放地址
     */
    public final static String WIN_LOG_PATH = "C:\\usr\\local\\data\\logs";
    /**
     * mac系统log日志存放地址
     */
    public final static String MAC_LOG_PATH = "/usr/local/data/logs";
    /**
     * linux系统log日志存放地址
     */
    public final static String LINUX_LOG_PATH = "/data/logs";
    /**
     * 网关设置deltaTime
     */
    public final static String DELTATIME = "deltatime";
    /**
     * USER信息
     */
    public final static String USER_INFO = "user";
    /**
     * nacos.config地址
     */
    public final static String NACOS_ADDRS = "nacos1.guangl.io:8848,nacos2.guangl.io:8848,nacos3.guangl.io:8848";
    /**
     * nacos.config.namespace地址
     */
    public final static String NACOS_NAMESPACE = "dcae3641-cf6a-40c1-82e6-956b5418cdae";
    /**
     * nacos.config.group
     */
    public final static String NACOS_GROUP = "massadobe-attempt-group";
    /**
     * nacos.config配置名
     */
    public final static String NACOS_FILE_NAME = "massadobe-attempt-dubbo-dev.yml";
    /**
     * nacos.config热更新
     */
    public final static boolean NACOS_REFRESH = true;
    /**
     * tk.mybatis扫描包址
     */
    public final static String TK_MYBATIS_SCAN_ADDR = "com.massadobe.attempt.application.dao";
}
