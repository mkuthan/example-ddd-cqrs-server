package example.ddd.domain;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.assertj.core.api.AbstractAssert;

import example.ddd.Event;
import example.ddd.EventPublisher;

public class EventPublisherAssert extends AbstractAssert<EventPublisherAssert, EventPublisher> {

	public EventPublisherAssert(EventPublisher actual) {
		super(actual, EventPublisherAssert.class);
	}

	public EventPublisherAssert published(Event event) {
		verify(actual).publish(eq(event));
		return this;
	}

	public EventPublisherAssert notPublished() {
		verifyNoMoreInteractions(actual);
		return this;
	}
}
