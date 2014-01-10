package example.scrumboard.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.ddd.infrastructure.DddInfrastructureConfig;

@Configuration
@ComponentScan
@Import({ DddInfrastructureConfig.class })
public class ScrumBoardInfrastructureConfig {
}
