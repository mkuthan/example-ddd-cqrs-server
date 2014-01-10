package example.ddd.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

import example.ddd.domain.Event;
import example.ddd.domain.EventPublisher;

@Component
public class GuavaEventBusEventPublisher implements EventPublisher {

	@Autowired
	private EventBus eventBus;

	public void publish(Event event) {
		eventBus.post(event);
	}

}