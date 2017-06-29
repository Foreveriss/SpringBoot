package cn.mldn.microboot;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import cn.mldn.vo.Member;

@SpringBootTest(classes = MemberServiceProviderStartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMemberController {
	private RestTemplate restTemplate = new RestTemplate() ;
	@Test
	public void testGet() {
		String url = "http://localhost:8001/member/get?mid=admin" ;
		Member vo = this.restTemplate.postForObject(url, null, Member.class) ;
		System.out.println(vo);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testAuth() { 
		String url = "http://localhost:8001/member/auth?mid=admin" ;
		Map<String,Object> map = this.restTemplate.postForObject(url, null, Map.class) ;
		Set<String> allRoles = new HashSet<String>() ;
		Set<String> allActions = new HashSet<String>() ;
		allRoles.addAll((List<String>) map.get("allRoles"));
		allActions.addAll((List<String>) map.get("allActions")) ;
		System.out.println("【角色】" + allRoles);
		System.out.println("【权限】" + allActions);
	}
}
