package com.springboot.config;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatasourceConfig {

	/** logger. */
	private Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);

	@Bean
	@ConfigurationProperties("sword.datasource")
	// 通过自定义配置的属性读取相关属性放入DataSourceProperties
	public DataSourceProperties swordDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource swordDataSource() {
		DataSourceProperties dataSourceProperties = swordDataSourceProperties();
		logger.info("sword datasource: {}", dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}


	//注入事务模板管理器
	@Bean
	@Resource
	public PlatformTransactionManager swordTxManager(DataSource swordDataSource) {
		return new DataSourceTransactionManager(swordDataSource);
	}

	@Bean
	@ConfigurationProperties("ruoyi.datasource")
	public DataSourceProperties ryDataSourceProperties(){
		return new DataSourceProperties();
	}

	@Bean
	public DataSource ryDataSource(){
		DataSourceProperties dataSourceProperties = ryDataSourceProperties();
		logger.info("ryDaaSource : {} " ,dataSourceProperties.getUrl());
		//return DataSourceBuilder.create().build();
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}


	@Bean
	@Resource // 将datasource 注入
	public PlatformTransactionManager ryTxManager(DataSource ryDataSource){
		return new DataSourceTransactionManager(ryDataSource);
	}

}
