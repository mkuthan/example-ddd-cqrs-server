package example.ddd;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

@Configuration
@Profile(DddConfig.PROFILE)
@ComponentScan
public class DddConfig {

	public static final String PROFILE = "ddd";

	@Autowired
	private Executor executor;

	@Bean
	@Autowired
	public EventBus eventBus() {
		return new AsyncEventBus(executor);
	}
}
