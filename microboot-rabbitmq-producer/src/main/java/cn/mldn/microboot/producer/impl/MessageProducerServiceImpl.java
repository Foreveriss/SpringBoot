package cn.mldn.microboot.producer.impl;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.microboot.config.ProducerConfig;
import cn.mldn.microboot.producer.IMessageProducerService;
@Service
public class MessageProducerServiceImpl implements IMessageProducerService {
	@Resource
	private RabbitTemplate rabbitTemplate;
	@Override
	public void sendMessage(String msg) {
		this.rabbitTemplate.convertAndSend(ProducerConfig.EXCHANGE,
				ProducerConfig.ROUTINGKEY, msg);
	}

}
