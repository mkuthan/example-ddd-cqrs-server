package example.ddd.scrumboard.domain.sprint;

import org.fest.assertions.GenericAssert;

public class SprintAssert extends GenericAssert<SprintAssert, Sprint> {

	public SprintAssert(Sprint actual) {
		super(SprintAssert.class, actual);
	}

}
