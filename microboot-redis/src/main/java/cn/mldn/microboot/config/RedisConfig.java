package cn.mldn.microboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.mldn.microboot.util.redis.RedisObjectSerializer;

@Configuration
public class RedisConfig {
	@Bean
	public RedisTemplate<String, Object> getRedisTemplate(
			RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
		redisTemplate.setValueSerializer(new RedisObjectSerializer()); // value的序列化类型
		return redisTemplate;
	}
}
