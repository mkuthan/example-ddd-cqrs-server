package example.scrumboard.config;

import java.util.concurrent.Executor;

import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.application.api.ReleaseService;
import example.scrumboard.application.api.SprintService;

@Configuration
//@formatter:off
@Import({
	// external modules
	BootstrapConfig.class, 
	DddConfig.class,
})
//@formatter:on
@PropertySource("classpath:/test.properties")
@Profile(ScrumBoardTestConfig.PROFILE)
public class ScrumBoardTestConfig {

	public static final String PROFILE = "test";
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public Executor asyncExecutor() {
		return new SyncTaskExecutor();
	}

	@Bean
	public ProductService productService() {
		return Mockito.mock(ProductService.class);
	}

	@Bean
	public ReleaseService releaseService() {
		return Mockito.mock(ReleaseService.class);
	}

	@Bean
	public SprintService sprintService() {
		return Mockito.mock(SprintService.class);
	}

}