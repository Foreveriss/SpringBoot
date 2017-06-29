package cn.mldn.microboot;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.mldn.microboot.service.IMessageProducerService;
@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMessageService {
	@Resource
	private IMessageProducerService messageService;
	@Test
	public void testStart() throws Exception {
		for (int x = 0; x < 100; x++) {
			this.messageService.send("mldn - " + x);
		}
	}
}
