package example.scrumboard.infrastructure.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import example.scrumboard.config.ScrumBoardConfig;

@Configuration
@Profile({ ScrumBoardConfig.Local.PROFILE, ScrumBoardConfig.Remote.PROFILE })
@EnableScheduling
public class ScrumBoardInfrastructureTaskConfig implements SchedulingConfigurer {

	@Value("${scheduling.taskExecutorCorePoolSize}")
	private int taskExecutorCorePoolSize;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExampleTaskExecutor-%d").build();
		return Executors.newScheduledThreadPool(taskExecutorCorePoolSize, threadFactory);
	}

}
