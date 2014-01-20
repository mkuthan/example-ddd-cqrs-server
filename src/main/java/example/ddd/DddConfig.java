package example.ddd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.eventbus.EventBus;

@Configuration
@Profile(DddConfig.PROFILE)
@ComponentScan
public class DddConfig {

	public static final String PROFILE = "ddd";

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}
}
