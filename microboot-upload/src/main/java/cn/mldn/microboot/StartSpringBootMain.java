package cn.mldn.microboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.multipart.MultipartFile;
@SpringBootApplication	// 启动SpringBoot程序，而后自带子包扫描
public class StartSpringBootMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartSpringBootMain.class, args);
    }
}
