package example.scrumboard.infrastructure.scheduling;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import example.scrumboard.config.ScrumBoardConfig;

@Configuration
@EnableAsync
public class ScrumBoardInfrastructureAsyncConfig implements AsyncConfigurer {

	@Autowired
	private Environment environment;

	@Autowired
	private Executor asyncExecutor;

	@Bean
	@Primary
	@Profile({ ScrumBoardConfig.Local.PROFILE, ScrumBoardConfig.Remote.PROFILE })
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(environment.getRequiredProperty("scheduling.asyncExecutorCorePoolSize", Integer.class));
		executor.setMaxPoolSize(environment.getRequiredProperty("scheduling.asyncExecutorMaxPoolSize", Integer.class));
		executor.setQueueCapacity(environment.getRequiredProperty("scheduling.asyncQueueCapacity", Integer.class));
		executor.setThreadNamePrefix("ExampleAsyncExecutor-");
		return executor;
	}

	@Override
	public Executor getAsyncExecutor() {
		return asyncExecutor;
	}

}
