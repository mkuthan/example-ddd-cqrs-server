package example.ddd.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import example.ddd.AggregateRoot;
import example.ddd.Event;

public class AggregateRootAssert extends AbstractAssert<AggregateRootAssert, AggregateRoot<?>> {

	public AggregateRootAssert(AggregateRoot<?> actual) {
		super(actual, AggregateRootAssert.class);
	}

	public AggregateRootAssert published(Event event) {
		assertThat(actual.getPendingEvents()).contains(event);
		return this;
	}

	public AggregateRootAssert notPublished() {
		assertThat(actual.getPendingEvents()).isEmpty();
		return this;
	}
}
