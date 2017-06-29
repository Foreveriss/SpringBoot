package cn.mldn.microboot;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.mldn.microboot.service.IMemberService;

@SpringBootTest(classes = MemberServiceProviderStartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMemberService {
	@Resource
	private IMemberService memberService ;
	@Test
	public void testGet() {
		System.out.println(this.memberService.get("admin"));
	}
	@Test
	public void testAuth() {
		System.out.println(this.memberService.listAuthByMember("admin"));
	}
}
