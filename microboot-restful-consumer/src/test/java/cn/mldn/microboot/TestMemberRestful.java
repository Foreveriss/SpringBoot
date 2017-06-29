package cn.mldn.microboot;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import cn.mldn.microboot.vo.Member;

@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMemberRestful {
	@Resource
	private RestTemplate restTemplate; 
	@Test
	public void testAdd() {
		Boolean flag = this.restTemplate.getForObject(
				"http://localhost:8080/member/add?mid=110&name=SMITH&age=10",
				Boolean.class);
		System.out.println("【ConsumerTest.add()】" + flag);
	}
	@Test
	public void testGet() {
		// 通过远程的Rest服务中的信息将其自动转换为Member对象实例
		Member member = this.restTemplate.getForObject(
				"http://localhost:8080/member/get?mid=110", Member.class);
		System.out.println("【ConsumerTest.get()】" + member);
	}
}
