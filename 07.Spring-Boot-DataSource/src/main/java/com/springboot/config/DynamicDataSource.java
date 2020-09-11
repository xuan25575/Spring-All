package com.springboot.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * AbstractRoutingDataSource
 * 这个类的作用就是为了设置数据源，这里重写了之后。
 * SpringBoot（覆盖）就通过这里进行ThreadLocal.get（）方法获取，然后设置数据源
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getDBType();
    }
}
