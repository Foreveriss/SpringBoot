package cn.mldn.microboot.config;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConnectorConfig { // 此类专门负责HTTP的连接的相关配置
	public Connector initConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol") ;
		connector.setScheme("http"); 	// 如果现在用户使用普通的http的方式进行访问
		connector.setPort(80);	// 用户访问的是80端口
		connector.setSecure(false); // 如果该连接为跳转则表示不是一个新的连接对象
		connector.setRedirectPort(443); 	// 设置转发操作端口
		return connector; 
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory servletContainerFactory() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
			protected void postProcessContext(
					org.apache.catalina.Context context) {// 该方法主要进行请求处理的上下文配置
				SecurityConstraint securityConstraint = new SecurityConstraint(); // 定义新的安全访问策略
				securityConstraint.setUserConstraint("CONFIDENTIAL"); 	// 定义用户的访问约束要求
				SecurityCollection collection = new SecurityCollection() ;
				collection.addPattern("/*"); // 匹配所有的访问映射路径
				securityConstraint.addCollection(collection);	// 追加路径映射访问配置
				context.addConstraint(securityConstraint);
			};
		};
		factory.addAdditionalTomcatConnectors(this.initConnector());
		return factory;
	}
}
 