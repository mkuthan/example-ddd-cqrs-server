package example.ddd.domain;

public class DddAssertions {

	public static EventPublisherAssert thenEvent(EventPublisher eventPublisher) {
		return new EventPublisherAssert(eventPublisher);
	}

}
