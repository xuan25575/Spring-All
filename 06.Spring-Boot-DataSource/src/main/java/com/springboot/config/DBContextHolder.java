package com.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBContextHolder {

    /** logger. */
    private static Logger logger = LoggerFactory.getLogger(DBContextHolder.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDBType(String dbType) {
        logger.info("设置数据源实例");
        contextHolder.set(dbType);
    }

    public static String getDBType() {
        logger.info("获取数据源实例");
        return contextHolder.get();
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}
