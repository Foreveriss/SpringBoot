package cn.mldn.microboot;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.mldn.microboot.vo.Member;
@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestRedisTwo {
	@Resource(name="redisTwo")
	private RedisTemplate<String, Object> redisTemplate;
	@Test 
	public void testGet() {
		System.out.println(this.redisTemplate.opsForValue().get("mldn"));
	}
	@Test
	public void testSet() {
		Member vo = new Member() ;
		vo.setMid("mldnjava");
		vo.setAge(19);
		this.redisTemplate.opsForValue().set("mldn", vo);;
	}
}
