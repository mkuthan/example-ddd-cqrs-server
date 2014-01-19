package example.scrumboard.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import example.scrumboard.application.api.ProductService;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class ScrumBoardRestCommonConfig {

	@Bean
	@Profile("mock")
	public ProductService productService() {
		return Mockito.mock(ProductService.class);
	}

}
