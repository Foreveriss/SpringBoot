package cn.mldn.microboot.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration // 定时调度的配置类一定要实现指定的父接口
public class SchedulerConfig implements SchedulingConfigurer {
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) { // 开启一个线程调度池
		taskRegistrar.setScheduler(Executors.newScheduledThreadPool(100));
	}

}
