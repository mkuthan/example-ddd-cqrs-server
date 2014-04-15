package example.ddd.domain;

import example.ddd.AggregateRoot;

public class DddAssertions {

	public static AggregateRootAssert assertThat(AggregateRoot<?> actual) {
		return new AggregateRootAssert(actual);
	}

}
