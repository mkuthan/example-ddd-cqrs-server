package example.scrumboard.domain.sprint;

import java.util.UUID;

public class SprintFactory {

	public Sprint create() {
		SprintId id = new SprintId(UUID.randomUUID().toString());

		return new Sprint(id);
	}
}
