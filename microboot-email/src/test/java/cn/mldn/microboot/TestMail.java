package cn.mldn.microboot;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMail {
	@Resource
	private JavaMailSender javaMailSender ;
	@Test
	public void testSendMail() {
		SimpleMailMessage message = new SimpleMailMessage() ;	// 要发送的消息内容
		message.setFrom("你自己的邮箱地址，110@qq.com");
		message.setTo("接收者的邮箱地址，220@qq.com");
		message.setSubject("测试邮件（来自老李的祝福，www.mldn.cn）");
		message.setText("好好学习，天天向上，学习就登录www.mldn.cn");
		this.javaMailSender.send(message);
	}
}
