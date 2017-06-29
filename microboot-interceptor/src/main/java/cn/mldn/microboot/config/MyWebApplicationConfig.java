package cn.mldn.microboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.mldn.util.MyInterceptor;

@Configuration
public class MyWebApplicationConfig extends WebMvcConfigurerAdapter {	// 定义MVC配置
	@Override
	public void addInterceptors(InterceptorRegistry registry) {	// 进行拦截器的注册处理操作
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**") ;	// 匹配路径
		super.addInterceptors(registry);
	}
}
