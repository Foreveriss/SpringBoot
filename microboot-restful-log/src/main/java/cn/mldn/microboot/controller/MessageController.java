package cn.mldn.microboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	private Logger log = LoggerFactory.getLogger(MessageController.class);
	@RequestMapping(value = "/test")
	public Object test() {
		this.log.info("【*** INFO ***】日志输出");
		this.log.error("【*** ERROR ***】日志输出");
		return true;
	}
}
