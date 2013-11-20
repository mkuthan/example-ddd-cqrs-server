package example.ddd.scrumboard.domain.shared;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.fest.assertions.GenericAssert;

public class EventPublisherAssert extends GenericAssert<EventPublisherAssert, EventPublisher> {

	public EventPublisherAssert(EventPublisher actual) {
		super(EventPublisherAssert.class, actual);
	}

	public EventPublisherAssert publishedEvent(DomainEvent event) {
		verify(actual).publish(eq(event));
		return this;
	}

	public EventPublisherAssert didNotPublishEvent() {
		verifyNoMoreInteractions(actual);
		return this;
	}
}
