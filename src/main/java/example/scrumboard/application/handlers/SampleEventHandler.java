package example.scrumboard.application.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;

import example.ddd.Event;
import example.ddd.EventHandler;

@MessageEndpoint
public class SampleEventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleEventHandler.class);

	@EventHandler
	public void log(Event event) {
		LOGGER.info("Event handled " + event);
	}

}
