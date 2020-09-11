package com.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.springboot.constant.DataSourceNames;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean(name = "first")
    @ConfigurationProperties(prefix = "spring.datasource.druid.first")
    public DataSource firstDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "second")
    @ConfigurationProperties(prefix = "spring.datasource.druid.second")
    public DataSource secondDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary//指定这个bean被主要加载，优先级最高。
    public DynamicDataSource dataSource(@Qualifier("first") DataSource firstDataSource, @Qualifier("second") DataSource secondDataSource){
        DynamicDataSource multipleDataSource = new DynamicDataSource();
        Map<Object,Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceNames.FIRST,firstDataSource);
        targetDataSources.put(DataSourceNames.SECOND,secondDataSource);
        // 添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        // 设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(firstDataSource);
        return multipleDataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() {
        DynamicDataSource dynamicDataSource = dataSource(firstDataSource(),secondDataSource());
        return new JdbcTemplate(dynamicDataSource);
    }
}
