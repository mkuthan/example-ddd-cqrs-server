package example.ddd.scrumboard.domain.sprint;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.GenericAssert;

public class SprintAssert extends GenericAssert<SprintAssert, Sprint> {

	public SprintAssert(Sprint actual) {
		super(SprintAssert.class, actual);
	}

	public SprintAssert hasId(SprintId id) {
		assertThat(actual.getId()).isEqualTo(id);
		return this;
	}

}
