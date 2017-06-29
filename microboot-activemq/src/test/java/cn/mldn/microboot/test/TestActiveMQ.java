package cn.mldn.microboot.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.mldn.microboot.StartSpringBootMain;
import cn.mldn.microboot.producer.IMessageProducerService;

@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {
	@Resource
	private IMessageProducerService messageProducer;
	@Test
	public void testSend() throws Exception {
		for (int x = 0; x < 100; x++) {
			this.messageProducer.sendMessage("mldn - " + x);
		}
	}
}
