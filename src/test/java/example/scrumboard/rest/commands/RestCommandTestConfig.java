package example.scrumboard.rest.commands;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import example.scrumboard.application.api.ProductService;
import example.scrumboard.application.api.ReleaseService;
import example.scrumboard.application.api.SprintService;
import example.scrumboard.infrastructure.rest.ScrumBoardInfrastructureRestConfig;

@Import({ ScrumBoardInfrastructureRestConfig.class, ScrumBoardRestCommandsConfig.class })
@PropertySource("classpath:/test.properties")
public class RestCommandTestConfig {

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