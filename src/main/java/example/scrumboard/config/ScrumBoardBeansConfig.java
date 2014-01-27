package example.scrumboard.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Configuration
public class ScrumBoardBeansConfig {

	@Autowired
	private Environment environment;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	@Qualifier("asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(environment.getRequiredProperty("scheduling.asyncExecutorCorePoolSize", Integer.class));
		executor.setMaxPoolSize(environment.getRequiredProperty("scheduling.asyncExecutorMaxPoolSize", Integer.class));
		executor.setQueueCapacity(environment.getRequiredProperty("scheduling.asyncQueueCapacity", Integer.class));
		executor.setThreadNamePrefix("ExampleAsyncExecutor-");
		return executor;
	}

	@Bean(destroyMethod = "shutdown")
	@Qualifier("taskExecutor")
	public Executor taskExecutor() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ExampleTaskExecutor-%d").build();
		return Executors.newScheduledThreadPool(
				environment.getRequiredProperty("scheduling.taskExecutorCorePoolSize", Integer.class), threadFactory);
	}

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}

	@Configuration
	@PropertySource("classpath:/local.properties")
	@Profile(Local.PROFILE)
	public static class Local {

		public static final String PROFILE = "local";

		@Bean
		public DataSource localDataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
		}

	}

	@Configuration
	@PropertySource("classpath:/remote.properties")
	@Profile(Remote.PROFILE)
	public static class Remote {

		public static final String PROFILE = "remote";

		@Bean
		public DataSource remoteDataSource() {
			// TODO: JNDI lookup
			return null;
		}

	}

}
