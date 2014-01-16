package example.scrumboard.rest.commands;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.scrumboard.application.api.ProductService;

@Configuration
public class ScrumBoardRestCommandsTestConfig {

	@Bean
	public ProductService productService() {
		return Mockito.mock(ProductService.class);
	}

}