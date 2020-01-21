package com.massadobe.attempt.common.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName: DynamicDataSource
 * @Author: MassAdobe
 * @Email: massadobe8@gmail.com
 * @Description: TODO
 * @Date: Created in 2019-12-13 11:59
 * @Version: 1.0.0
 * @param: * @param null
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public static enum DatabaseType {
        Read, Write
    }

    public static void master() {
        contextHolder.set(DatabaseType.Read);
    }


    public static void slave() {
        contextHolder.set(DatabaseType.Write);
    }

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getType() {
        return contextHolder.get();
    }
}
