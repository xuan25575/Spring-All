package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

/**
 * JdbcTemplate 常用的
 * simpleJdbcInsert
 * NamedParameterJdbcTemplate
 *
 * 上面两个类都可以使用 SqlParameterSource
 *
 * SqlParameterSource接口对sql参数进行了封装，两个常用的实现BeanPropertySqlParameterSource和MapSqlParameterSource，
 * BeanPropertySqlParameterSource可以通过javabean构造，MapSqlParameterSource则可以用map构造
 */
@Configuration
public class DataSourceConfig {


    @Autowired
    JdbcTemplate jdbcTemplate;

    /***
     * NamedParameterJdbcTemplate主要提供以下三类方法：
     * execute方法、query及queryForXXX方法、update及batchUpdate方法。
     * 命名参数设值有两种方式：java.util.Map和SqlParameterSource：
     *
     * 1）Map：使用Map键数据来对于命名参数，而Map值数据用于设值；
     *
     * 2）SqlParameterSource：可以使用SqlParameterSource实现作为来实现为命名参数设值，
     *  默认有MapSqlParameterSource和BeanPropertySqlParameterSource实现；
     *  MapSqlParameterSource实现非常简单，只是封装了java.util.Map；
     *  而BeanPropertySqlParameterSource封装了一个JavaBean对象，
     *  通过JavaBean对象属性来决定命名参数的值。
     *
     *  实用类：分别是BeanPropertySqlParameterSource、BeanPropertyRowMapper。
     *  1.BeanPropertySqlParameterSource：可以把实例转化成SqlParameterSource
     *  例，new BeanPropertySqlParameterSource(new User);
     *  2.BeanPropertyRowMapper：可以把返回的每一行转化成对应的对像
     *  例 new BeanPropertyRowMapper<>(CustomSearchVo.class);
     *
     *  具体看代码
     * @param dataSource 通过 @Autowired 注入了
     * @return NamedParameterJdbcTemplate
     */
    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 1. 提供的一个简化插入操作的类
     * 2. 通过withTableName绑定需要操作的数据库表，然后指定新增的参数
     * 3. 通过usingGeneratedKeyColumns 指定主键，
     *    executeAndReturnKey执行并返回主键， 返回的主键为Number类型，如有需要，自行转换
     * 4. usingColumns方法可以限制插入的列 标示可以插入的列.
     *
     * @param dataSource 通过 @Autowired 注入了
     * @return SimpleJdbcInsert
     */
    @Bean
    @Autowired
    public SimpleJdbcInsert simpleJdbcInsert(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource);
    }

}
