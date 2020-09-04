package com.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 多数据源配置的方式2
 * 1.通过@Primary注解指定数据源
 * 2.去除自动装配的数据源的配置
 *  - 然后可以自己配置多个数据源了。
 *  但是这个目前还不知道怎么使用.
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
public class Application implements CommandLineRunner {
	/** logger. */
	private Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	@Qualifier(value = "swordDataSource")
	private DataSource dataSource;

	// 不能使用
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Override
	public void run(String... args) throws Exception {
		showConnection();
	}

	private void showConnection() throws SQLException {
		logger.info("dataSource = {} ",dataSource.toString());
		Connection conn = dataSource.getConnection();
		logger.info("conn = {} ",conn.toString());
		conn.close();
	}

//	private void showData() {
//		jdbcTemplate.queryForList("SELECT * FROM FOO")
//				.forEach(row -> logger.info(row.toString()));
//	}


}
