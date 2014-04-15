package example.scrumboard.application.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import example.ddd.Event;

@MessageEndpoint
public class SampleEventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleEventHandler.class);

	// TODO: use @EventHandler, see https://jira.spring.io/browse/INT-3373
	@ServiceActivator(inputChannel = "eventBus")
	public void log(Event event) {
		LOGGER.info("Event handled " + event);
	}

}
