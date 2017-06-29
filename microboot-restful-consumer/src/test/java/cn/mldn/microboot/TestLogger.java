package cn.mldn.microboot;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import cn.mldn.microboot.vo.LogInfo;

@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestLogger {
	@Resource
	private RestTemplate restTemplate;
	@Test
	public void testLevel() {
		LogInfo log = new LogInfo();
		log.setLevel("INFO"); // 新的日志级别
		this.restTemplate.postForLocation(
				"http://localhost:8080/loggers/cn.mldn.microboot.controller",
				log);
	}
}
