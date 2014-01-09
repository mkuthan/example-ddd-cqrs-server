package example.scrumboard.domain.backlog.task;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.ValueObject;

public class TaskId extends ValueObject {

	private static final long serialVersionUID = 1L;

	private String id;

	TaskId() {
	}

	public TaskId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
