package cn.mldn.microboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication	// 启动SpringBoot程序，而后自带子包扫描
@ComponentScan("cn.mldn.microboot")
public class StartSpringBootMain {
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartSpringBootMain.class, args);
    }
}
