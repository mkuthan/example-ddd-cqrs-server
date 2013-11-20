package example.ddd.scrumboard.domain.sprint;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import example.ddd.scrumboard.domain.shared.EventPublisher;

@Test
public class SprintTest {

	private Sprint sprint;

	private SprintBuilder builder;

	private EventPublisher eventPublisher;

	@BeforeTest
	protected void initializeBuilder() {
		eventPublisher = Mockito.mock(EventPublisher.class);
		builder = new SprintBuilder().sprint(eventPublisher).withId(new SprintId("any id"));
	}

	public void shouldCreate() {
		SprintId id = new SprintId("id");

		givenSprint().withId(id);

		whenSprint();

		// TODO
		// thenSprint().todo();
	}

	private SprintBuilder givenSprint() {
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
