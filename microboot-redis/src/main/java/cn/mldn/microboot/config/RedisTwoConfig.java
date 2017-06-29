package cn.mldn.microboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.mldn.microboot.util.redis.RedisObjectSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisTwoConfig {
	public RedisConnectionFactory getRedisConnectionFactory(String hostName,
			String password, int port, int maxActive, int maxIdle, int minIdle,
			long maxWait, int database) { // 是负责建立Factory的连接工厂类
		JedisConnectionFactory jedisFactory = new JedisConnectionFactory();
		jedisFactory.setHostName(hostName);
		jedisFactory.setPort(port);
		jedisFactory.setPassword(password);
		jedisFactory.setDatabase(database);
		JedisPoolConfig poolConfig = new JedisPoolConfig(); // 进行连接池配置
		poolConfig.setMaxTotal(maxActive);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWait);
		jedisFactory.setPoolConfig(poolConfig);
		jedisFactory.afterPropertiesSet(); // 初始化连接池配置
		return jedisFactory;
	}
	@Bean("redisTwo")
	public RedisTemplate<String, Object> getRedisTemplate(
			@Value("${spring.redis-two.host}") String hostName,
			@Value("${spring.redis-two.password}") String password,
			@Value("${spring.redis-two.port}") int port,
			@Value("${spring.redis-two.database}") int database,
			@Value("${spring.redis-two.pool.max-active}") int maxActive,
			@Value("${spring.redis-two.pool.max-idle}") int maxIdle,
			@Value("${spring.redis-two.pool.min-idle}") int minIdle,
			@Value("${spring.redis-two.pool.max-wait}") long maxWait) {

		RedisConnectionFactory factory = this.getRedisConnectionFactory(
				hostName, password, port, maxActive, maxIdle, minIdle, maxWait,
				database); // 建立Redis的连接
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
		redisTemplate.setValueSerializer(new RedisObjectSerializer()); // value的序列化类型
		return redisTemplate;
	}
}
