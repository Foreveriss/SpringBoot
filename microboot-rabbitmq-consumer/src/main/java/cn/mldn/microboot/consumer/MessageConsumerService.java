package cn.mldn.microboot.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {
	@RabbitListener(queues="mldn.microboot.queue")
	public void receiveMessage(String text) {	// 进行消息接收处理
		System.err.println("【*** 接收消息 ***】" + text);
	}
}
