package example.ddd.scrumboard.domain.shared;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.GenericAssert;

public class AggregateRootAssert extends GenericAssert<AggregateRootAssert, AggregateRoot<?>> {
	
	public AggregateRootAssert(AggregateRoot<?> actual) {
		super(AggregateRootAssert.class, actual);
	}

	public AggregateRootAssert hasId(UniqueIdentifier<?> id) {
		assertThat(actual.getId()).isEqualTo(id);
		return this;
	}

}
