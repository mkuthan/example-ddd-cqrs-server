package example.scrumboard.infrastructure.shared;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import example.scrumboard.config.ScrumBoardBeansConfig;

@Configuration
@Profile({ ScrumBoardBeansConfig.Local.PROFILE, ScrumBoardBeansConfig.Remote.PROFILE })
@EnableScheduling
public class ScrumBoardInfrastructureTaskConfig implements SchedulingConfigurer {

	@Autowired
	private Environment environment;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	@Qualifier("taskExecutor")
	public Executor taskExecutor() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExampleTaskExecutor-%d").build();
		return Executors.newScheduledThreadPool(
				environment.getRequiredProperty("scheduling.taskExecutorCorePoolSize", Integer.class), threadFactory);
	}

}
