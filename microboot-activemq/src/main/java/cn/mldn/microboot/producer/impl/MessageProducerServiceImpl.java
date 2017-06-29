package cn.mldn.microboot.producer.impl;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.microboot.producer.IMessageProducerService;
@Service
public class MessageProducerServiceImpl implements IMessageProducerService {
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Resource
	private Queue queue;
	@Override
	public void sendMessage(String msg) {
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}

}
