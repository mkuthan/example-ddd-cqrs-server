package example.ddd.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;

import example.ddd.domain.Event;

@Component
public class EventLoggingSubscriber {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventLoggingSubscriber.class);

	@Subscribe
	public void log(Event event) {
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace(event.toString());
		}
	}

}
