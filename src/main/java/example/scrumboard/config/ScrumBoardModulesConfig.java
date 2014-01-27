package example.scrumboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;
import example.scrumboard.application.ScrumBoardApplicationConfig;
import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;
import example.scrumboard.infrastructure.rest.ScrumBoardInfrastructureRestConfig;
import example.scrumboard.infrastructure.scheduling.ScrumBoardInfrastructureAsyncConfig;
import example.scrumboard.infrastructure.scheduling.ScrumBoardInfrastructureTaskConfig;
import example.scrumboard.infrastructure.shared.ScrumBoardInfrastructureSharedConfig;
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
	ScrumBoardInfrastructureRestConfig.class,
	ScrumBoardInfrastructureSharedConfig.class,
	ScrumBoardInfrastructureTaskConfig.class,
	// core modules
	ScrumBoardApplicationConfig.class,
	ScrumBoardDomainConfig.class,
	ScrumBoardRestCommandsConfig.class,
	ScrumBoardRestQueriesConfig.class,
})
//@formatter:on
public class ScrumBoardModulesConfig {
}