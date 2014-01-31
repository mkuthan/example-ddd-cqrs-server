package example.scrumboard.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import example.scrumboard.application.ScrumBoardApplicationConfig;
import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.bootstrap.ScrumBoardInfrastructureBootstrapConfig;
import example.scrumboard.infrastructure.events.ScrumBoardInfrastructureEventsConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;
import example.scrumboard.infrastructure.rest.ScrumBoardInfrastructureRestConfig;
import example.scrumboard.infrastructure.shared.ScrumBoardInfrastructureAsyncConfig;
import example.scrumboard.infrastructure.shared.ScrumBoardInfrastructureContextConfig;
import example.scrumboard.infrastructure.shared.ScrumBoardInfrastructureTaskConfig;
import example.scrumboard.infrastructure.shared.ScrumBoardInfrastructureTransactionConfig;
import example.scrumboard.rest.commands.ScrumBoardRestCommandsConfig;
import example.scrumboard.rest.queries.ScrumBoardRestQueriesConfig;

@Configuration
//@formatter:off
@Import({ 
	// infrastructure shared modules
	ScrumBoardInfrastructureAsyncConfig.class,
	ScrumBoardInfrastructureContextConfig.class,
	ScrumBoardInfrastructureTaskConfig.class,	
	ScrumBoardInfrastructureTransactionConfig.class,	
	// infrastructure modules
	ScrumBoardInfrastructureBootstrapConfig.class, 
	ScrumBoardInfrastructureEventsConfig.class, 
	ScrumBoardInfrastructureJpaConfig.class,
	ScrumBoardInfrastructureRestConfig.class,
	// core modules
	ScrumBoardApplicationConfig.class,
	ScrumBoardDomainConfig.class,
	ScrumBoardRestCommandsConfig.class,
	ScrumBoardRestQueriesConfig.class,
})
//@formatter:on
public class ScrumBoardConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Configuration
	@PropertySource("classpath:/local.properties")
	@Profile(Local.PROFILE)
	public static class Local {

		public static final String PROFILE = "local";

		@Bean
		public DataSource dataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
		}

	}

	@Configuration
	@PropertySource("classpath:/remote.properties")
	@Profile(Remote.PROFILE)
	public static class Remote {

		public static final String PROFILE = "remote";

		@Bean
		public DataSource dataSource() {
			// TODO: JNDI lookup
			return null;
		}

	}

}