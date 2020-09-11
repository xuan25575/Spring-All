package com.springboot.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.springboot.pojo.Student;

@CacheConfig(cacheNames = "student")
public interface StudentService {

	/**
	 * SPEL
	 * 具体可以看参数注释有详细描述。
	 *
	 * CachePut 每次调用都会更新这个key
	 * @param student student
	 * @return
	 */
	@CachePut(key = "#p0.id")
	Student update(Student student);

	//明确指定了 allEntries 为true 不允许指定key
	@CacheEvict(value = "query", allEntries = true)
	void delete(Integer id);

	//Cacheable 每次都会从缓存中取值。
	// 缓存中没有才会从数据库取值
	@Cacheable(key = "#p0")
	Student queryById(Integer id);
}
