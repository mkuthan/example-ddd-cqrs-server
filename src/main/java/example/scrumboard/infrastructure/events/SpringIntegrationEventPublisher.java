package example.scrumboard.infrastructure.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import example.ddd.Event;
import example.ddd.EventPublisher;

@Component
public class SpringIntegrationEventPublisher implements EventPublisher {

	@Autowired
	private MessagingTemplate messagingTemplate;

	@Override
	public void publish(Event event) {
		Message<Event> message = MessageBuilder.withPayload(event).build();
		messagingTemplate.send(message);
	}

}
