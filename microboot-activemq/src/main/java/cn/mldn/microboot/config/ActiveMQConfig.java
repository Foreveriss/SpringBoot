package cn.mldn.microboot.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class ActiveMQConfig {
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("mldn.msg.queue") ;
	}
}
