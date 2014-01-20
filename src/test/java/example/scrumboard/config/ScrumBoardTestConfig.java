package example.scrumboard.config;

import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
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
public class ScrumBoardTestConfig {

	public static final String PROFILE = "test";

	@Bean
	@Profile(PROFILE)
	public DataSource localDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	@Profile(PROFILE)
	public ProductService productService() {
		return Mockito.mock(ProductService.class);
	}

	@Bean
	@Profile(PROFILE)
	public ReleaseService releaseService() {
		return Mockito.mock(ReleaseService.class);
	}

	@Bean
	@Profile(PROFILE)
	public SprintService sprintService() {
		return Mockito.mock(SprintService.class);
	}

}