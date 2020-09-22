package com.springboot.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.test.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * 提供 基础的CURD  的功能
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}