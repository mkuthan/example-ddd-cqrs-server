package example.scrumboard.domain.sprint;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.ValueObject;

public class SprintId extends ValueObject {

	private static final long serialVersionUID = 1L;

	private String id;

	public SprintId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
