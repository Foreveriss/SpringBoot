package cn.mldn.microboot.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache<K, V> implements Cache<K, V> {
	private Log log = LogFactory.getLog(RedisCache.class);
	private RedisTemplate<String, Object> redisTempate; // 要提供有Redis处理工具类
	public RedisCache(RedisTemplate<String, Object> redisTempate) {
		this.redisTempate = redisTempate;
	}
	@Override
	public V get(K key) throws CacheException {
		log.info("### get() : K = " + key);
		return (V) this.redisTempate.opsForValue().get(key.toString());
	}
	@Override
	public V put(K key, V value) throws CacheException {
		log.info("### put() : K = " + key + "、V = " + value);
		this.redisTempate.opsForValue().set(key.toString(), value);
		return value;
	}

	@Override
	public V remove(K key) throws CacheException {
		log.info("### remove() : K = " + key);
		V val = this.get(key);
		this.redisTempate.delete(key.toString());
		return val;
	}

	@Override
	public void clear() throws CacheException {
		log.info("### clear()");
		this.redisTempate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb(); // 清空数据库
				return true;
			}
		});
	}

	@Override
	public int size() {
		log.info("### size()");
		return this.redisTempate.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.keys("*".getBytes()).size();
			}
		});
	}

	@Override
	public Set<K> keys() {
		log.info("### keys()");
		return this.redisTempate.execute(new RedisCallback<Set<K>>() {
			@Override
			public Set<K> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<K> set = new HashSet<K>();
				Set<byte[]> keys = connection.keys("*".getBytes());
				Iterator<byte[]> iter = keys.iterator();
				while (iter.hasNext()) {
					set.add((K) iter.next());
				}
				return set;
			}
		});
	}

	@Override
	public Collection<V> values() {
		log.info("### values()");
		return this.redisTempate.execute(new RedisCallback<Set<V>>() {
			@Override
			public Set<V> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<V> set = new HashSet<V>();
				Set<byte[]> keys = connection.keys("*".getBytes());
				Iterator<byte[]> iter = keys.iterator();
				while (iter.hasNext()) {
					set.add((V) connection.get(iter.next()));
				}
				return set;
			}
		});
	}
}
