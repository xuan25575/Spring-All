package com.springboot.service;

import com.springboot.pojo.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


@CacheConfig(cacheNames = "user")
public interface UserService {

	//缓存放入
	@CachePut(key = "#p0.id")
	User update(User student);


	// 缓存移除
	@CacheEvict(key = "#p0", allEntries = true)
	void deleteStudentById(Integer id);


	@Cacheable(key = "#p0")
	User queryStudentById(Integer id);
}
