package cn.mldn.microboot.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.mldn.microboot.StartSpringBootMain;
import cn.mldn.microboot.service.IDeptService;
import cn.mldn.microboot.vo.Dept;

@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestDeptService {
	@Resource
	private IDeptService deptService;
	@Test
	public void testList() throws Exception {
		System.out.println(this.deptService.list());
	}
	@Test
	public void testAdd() throws Exception {
		Dept dept = new Dept();
		dept.setDname("测试部");
		System.out.println(this.deptService.add(dept));
	}
}
