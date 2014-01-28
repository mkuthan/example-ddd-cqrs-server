package example.ddd.domain;

import example.ddd.EventPublisher;

public class DddAssertions {

	public static EventPublisherAssert thenEvent(EventPublisher eventPublisher) {
		return new EventPublisherAssert(eventPublisher);
	}

}
