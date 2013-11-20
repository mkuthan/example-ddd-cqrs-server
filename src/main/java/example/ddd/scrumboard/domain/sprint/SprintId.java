package example.ddd.scrumboard.domain.sprint;

import example.ddd.scrumboard.domain.shared.UniqueIdentifier;

public class SprintId extends UniqueIdentifier<String> {

	public SprintId(String id) {
		super(id);
	}

}
