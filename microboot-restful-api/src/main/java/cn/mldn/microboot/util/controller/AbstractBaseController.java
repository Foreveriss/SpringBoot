package cn.mldn.microboot.util.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

public abstract class AbstractBaseController {
	@Resource
	private MessageSource messageSource; // 自动注入此资源对象
	public String getMessage(String key, String... args) {
		return this.messageSource.getMessage(key, args, Locale.getDefault()); 
	}
}
