package example.bootstrap.infrastructure;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import example.bootstrap.domain.BootstrapEvent;

@Component
public class BootstrapEventPublisher implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	@Async
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		if (isRootApplicationContext(applicationContext)) {
			applicationContext.publishEvent(new BootstrapEvent(applicationContext));
		}
	}

	private boolean isRootApplicationContext(ApplicationContext applicationContext) {
		return applicationContext.getParent() == null;
	}

}
