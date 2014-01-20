package example.bootstrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.eventbus.EventBus;

@Configuration
@Profile(BootstrapConfig.PROFILE)
@ComponentScan
public class BootstrapConfig {

	public static final String PROFILE = "bootstrap";

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}
}
