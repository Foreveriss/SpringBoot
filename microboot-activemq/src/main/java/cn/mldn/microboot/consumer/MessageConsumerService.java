package cn.mldn.microboot.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {
	@JmsListener(destination="mldn.msg.queue")
	public void receiveMessage(String text) {	// 进行消息接收处理
		System.err.println("【*** 接收消息 ***】" + text);
	}
}
