package example.scrumboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.scrumboard.application.ScrumBoardApplicationConfig;
import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;
import example.scrumboard.rest.commands.ScrumBoardRestCommandsConfig;
import example.scrumboard.rest.queries.ScrumBoardRestQueriesConfig;

@Configuration
//@formatter:off
@Import({ 
	ScrumBoardCommonConfig.class, 
	ScrumBoardDataSourcesConfig.class, 
	ScrumBoardApplicationConfig.class,
	ScrumBoardDomainConfig.class, 
	ScrumBoardInfrastructureJpaConfig.class,
	ScrumBoardRestCommonConfig.class,
	ScrumBoardRestCommandsConfig.class,
	ScrumBoardRestQueriesConfig.class
})
//@formatter:on
public class ScrumBoardConfig {
}