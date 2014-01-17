package example.scrumboard.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;

@Configuration
//@formatter:off
@Import({ 
	ScrumBoardInfrastructureJpaConfig.class 
})
//@formatter:on
public class ScrumBoardInfrastructureConfig {
}
