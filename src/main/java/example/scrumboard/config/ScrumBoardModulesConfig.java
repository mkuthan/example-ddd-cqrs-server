package example.scrumboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
public class ScrumBoardModulesConfig {
}