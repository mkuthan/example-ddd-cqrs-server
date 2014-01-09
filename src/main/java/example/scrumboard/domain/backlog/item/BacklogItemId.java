package example.scrumboard.domain.backlog.item;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.ValueObject;

public class BacklogItemId extends ValueObject {

	private static final long serialVersionUID = 1L;

	private String id;

	BacklogItemId() {
	}

	public BacklogItemId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
