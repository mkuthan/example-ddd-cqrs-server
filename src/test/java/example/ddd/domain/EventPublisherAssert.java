package example.ddd.domain;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.assertj.core.api.AbstractAssert;

import example.ddd.domain.Event;
import example.ddd.domain.EventPublisher;

public class EventPublisherAssert extends AbstractAssert<EventPublisherAssert, EventPublisher> {

	public EventPublisherAssert(EventPublisher actual) {
		super(actual, EventPublisherAssert.class);
	}

	public EventPublisherAssert eventPublished(Event event) {
		verify(actual).publish(eq(event));
		return this;
	}

	public EventPublisherAssert eventWasNotPublished() {
		verifyNoMoreInteractions(actual);
		return this;
	}
}
