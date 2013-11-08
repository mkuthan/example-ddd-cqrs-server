package example.ddd.scrumboard.domain.sprint;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class SprintTest {

	private Sprint sprint;

	private Sprint.Builder builder;

	@BeforeTest
	protected void initializeBuilder() {
		builder = new Sprint.Builder().withId(new SprintId("any id"));
	}

	public void shouldCreate() {
		SprintId id = new SprintId("id");

		givenSprint().withId(id);

		whenSprint();

		thenSprint().hasId(id);
	}

	private Sprint.Builder givenSprint() {
		return builder;
	}

	private Sprint whenSprint() {
		this.sprint = builder.build();
		return sprint;
	}

	private SprintAssert thenSprint() {
		return new SprintAssert(sprint);
	}

}
