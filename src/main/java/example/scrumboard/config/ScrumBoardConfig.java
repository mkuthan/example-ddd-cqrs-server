package example.scrumboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;
import example.scrumboard.application.ScrumBoardApplicationConfig;
import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;
import example.scrumboard.infrastructure.rest.ScrumBoardInfrastructureRestConfig;
import example.scrumboard.infrastructure.scheduling.ScrumBoardInfrastructureAsyncConfig;
import example.scrumboard.infrastructure.scheduling.ScrumBoardInfrastructureSchedulingConfig;
import example.scrumboard.rest.commands.ScrumBoardRestCommandsConfig;
import example.scrumboard.rest.queries.ScrumBoardRestQueriesConfig;

@Configuration
//@formatter:off
@Import({ 
	// external modules
	BootstrapConfig.class, 
	DddConfig.class,
	// infrastructure modules
	ScrumBoardInfrastructureAsyncConfig.class,
	ScrumBoardInfrastructureJpaConfig.class,
	ScrumBoardInfrastructureSchedulingConfig.class,
	ScrumBoardInfrastructureRestConfig.class,
	// core modules
	ScrumBoardApplicationConfig.class,
	ScrumBoardDomainConfig.class,
	ScrumBoardRestCommandsConfig.class,
	ScrumBoardRestQueriesConfig.class,
})
//@formatter:on
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ScrumBoardConfig {

	@Bean
	@Profile({ Local.PROFILE, Remote.PROFILE })
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Configuration
	@PropertySource("classpath:/local.properties")
	@Profile(Local.PROFILE)
	public static class Local {
		public static final String PROFILE = "local";
	}

	@Configuration
	@PropertySource("classpath:/remote.properties")
	@Profile(Remote.PROFILE)
	public static class Remote {
		public static final String PROFILE = "remote";
	}

}