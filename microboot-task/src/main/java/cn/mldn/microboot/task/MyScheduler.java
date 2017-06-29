package cn.mldn.microboot.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {
	@Scheduled(fixedRate = 2000) // 采用间隔调度，每2秒执行一次
	public void runJobA() { // 定义一个要执行的任务
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("【*** MyTaskA - 间隔调度 ***】"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.format(new Date()));
	}
	@Scheduled(cron = "* * * * * ?") // 每秒调用一次
	public void runJobB() {
		System.err.println("【*** MyTaskB - 间隔调度 ***】"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.format(new Date()));
	}
}
