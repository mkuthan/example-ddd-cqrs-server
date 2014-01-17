package example.scrumboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.scrumboard.application.ScrumBoardApplicationConfig;
import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.config.ScrumBoardInfrastructureConfig;
import example.scrumboard.rest.config.ScrumBoardRestConfig;

@Configuration
//@formatter:off
@Import({ 
	ScrumBoardCommonConfig.class, 
	ScrumBoardDataSourcesConfig.class, 
	ScrumBoardApplicationConfig.class,
	ScrumBoardDomainConfig.class, 
	ScrumBoardInfrastructureConfig.class,
	ScrumBoardRestConfig.class 
})
//@formatter:on
public class ScrumBoardConfig {
}