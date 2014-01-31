package example.scrumboard.application.subscribers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import example.ddd.Event;

@MessageEndpoint
public class SampleSubsriber {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleSubsriber.class);

	@ServiceActivator(inputChannel = "eventBus")
	public void log(Event event) {
		LOGGER.info("Event handled " + event);
	}

}
