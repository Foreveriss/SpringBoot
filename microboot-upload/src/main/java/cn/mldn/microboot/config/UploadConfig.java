package cn.mldn.microboot.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadConfig { 
	@Bean
	public MultipartConfigElement getMultipartConfig() {
		MultipartConfigFactory config = new MultipartConfigFactory() ;
		config.setMaxFileSize("10MB"); 	// 设置上传文件的单个大小限制
		config.setMaxRequestSize("100MB"); 	// 设置总的上传的大小限制
		config.setLocation("/"); // 设置临时保存目录
		return config.createMultipartConfig() ;	// 创建一个上传配置
	}
}
