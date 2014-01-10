package example.ddd.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.google.common.eventbus.EventBus;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class DddInfrastructureConfig {

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}
}
