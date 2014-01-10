package example.ddd.infrastructure;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

import example.ddd.domain.ApplicationBootstrapEvent;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationBootstrap.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		if (isRootApplicationContext(applicationContext)) {
			LOGGER.info("Start application bootstrap");

			Stopwatch stopwatch = publishEvent(applicationContext);

			LOGGER.info("Finish application bootstrap in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
		}
	}

	private boolean isRootApplicationContext(ApplicationContext applicationContext) {
		return applicationContext.getParent() == null;
	}

	private Stopwatch publishEvent(ApplicationContext applicationContext) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		applicationContext.publishEvent(new ApplicationBootstrapEvent(applicationContext));
		return stopwatch.stop();
	}

}
