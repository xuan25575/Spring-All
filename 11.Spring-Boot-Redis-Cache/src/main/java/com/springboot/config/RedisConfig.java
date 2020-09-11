package com.springboot.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 *
 * RedisAutoConfiguration 可知
 * 2.RedisConnectionFactory 这个对象已经持有了。可以直接用的。
 * 在通过相关属性配置后，redis连接工厂已经加载进入容器了。 或者手动配置
 *  RedisConnectionFactory
 *  相关属性都在RedisProperties
 *
 **  CachingConfigurerSupport(Spring的抽象) 允许子类实现自己敢兴趣的方法。
 *   1.keyGenerator
 *   2.cacheManager
 *
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

	/**
	 * 自定义缓存key生成策略.
	 * 似乎没有生效
	 * @return
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	/**
	 * 缓存管理器
	 * 配置RedisCacheManager
	 * 1.builder()
	 * 2.create()
	 *
	 * @param factory
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		//配置缓存的配置
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofDays(1));
		//RedisCacheManager cacheManager = RedisCacheManager.builder(factory).cacheDefaults(redisCacheConfiguration).build();
		RedisCacheManager cacheManager = RedisCacheManager.builder(factory).build();
		return cacheManager;
	}

	/**
	 * 配置一个StringRedisTemplate.
	 * @param factory factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		setSerializer(template);// 设置序列化工具
		template.afterPropertiesSet();
		return template;
	}

	private void setSerializer(StringRedisTemplate template) {
		//使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
		@SuppressWarnings({"rawtypes", "unchecked"})
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
//		template.setKeySerializer(jackson2JsonRedisSerializer);
//		template.setValueSerializer(jackson2JsonRedisSerializer);
//		template.setHashKeySerializer(jackson2JsonRedisSerializer);
//		template.setHashValueSerializer(jackson2JsonRedisSerializer);
	}


//	@Bean
//	public RedisTemplate<String, String> customizeRedisTemplate(RedisConnectionFactory factory) {
//		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//		redisTemplate.setConnectionFactory(factory);
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.setValueSerializer(new StringRedisSerializer());
//		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//		return redisTemplate;
//	}
}