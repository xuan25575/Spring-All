package com.springboot.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TKMytis框架
 * 1.配合JPA的注解使用 @Id @Table 等
 * 2.创建一个baseMapper
 *  public interface BaseMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>,
 *  MySqlMapper<T>, IdsMapper<T>, ConditionMapper<T>,ExampleMapper<T> {
 *  }
 *  说明那些mapper
 *  （1）tk.mybatis.mapper.common.BaseMapper<T>中有较多方法，均需要继承实现. 基础增删改查
 *
 *   (2)MySqlMapper<T>中的方法如下：
 *    // 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
 *    public int insertList(List<T> recordList);
 *    // 插入数据，限制为实体包含`id`属性并且必须为自增列，实体配置的主键策略无效
 *    public int insertUseGeneratedKeys(T record);
 *    这两个方法就比较坑了，限制了主键必须为自增列，如果是自己生成主键则不能使用该方法。
 *
 *   (3)IdsMapper<T>中的方法如下：
 *   List<T> selectByIds(String ids);
 *   int deleteByIds(String ids);
 *
 *   // 传入的Object condition应为tk.mybatis.mapper.entity.Condition
 *   (4) ConditionMapper<T>中的方法如下：
 *
 *    public List<T> selectByCondition(Object condition);
 *    public int selectCountByCondition(Object condition);
 *    public int deleteByCondition(Object condition);
 *    public int updateByCondition(T record, Object condition);
 *    //根据Condition条件更新实体`record`包含的全部属性，null值不会被更新，返回更新的条数
 *    public int updateByConditionSelective(T record, Object condition);
 *
 *
 *   //传入的Object example应为tk.mybatis.mapper.entity.Example，
 *   (5)ExampleMapper<T>中的方法如下：
 *
 *    public T selectOneByExample(Object example);
 *    public int selectCountByExample(Object example);
 *    public int updateByExample(T record, Object example);
 *    public int updateByExampleSelective(T record, Object example);
 *
 *   Condition condition = new Condition(UserRole.class);
 *   Criteria criteria1 = condition.createCriteria();
 *   Criteria criteria1 = condition.and();
 *
 *   Example.and()/or()和Condition.and()/or()方法说明：
 *   每个Criteria在最终结果中以括号形式展现，此时and()方法则表示 and (Criteria中的条件)，or()方法则表示
 *   or （Criteria中的条件），
 *   默认createCriteria()等同于and()，
 *
 *   已经替换成Mybatis plus
 *   使用更简单和便捷
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.springboot.test.mapper")
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
