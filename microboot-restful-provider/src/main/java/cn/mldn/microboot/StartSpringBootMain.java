package cn.mldn.microboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 启动SpringBoot程序，而后自带子包扫描
public class StartSpringBootMain {
	static {
		System.setProperty("java.security.auth.login.config",
				"d:/kafka_client_jaas.conf"); // 表示系统环境属性
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(StartSpringBootMain.class, args);
	}
}
