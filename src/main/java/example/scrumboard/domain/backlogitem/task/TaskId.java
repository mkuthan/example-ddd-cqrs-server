package example.scrumboard.domain.backlogitem.task;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.ddd.ValueObject;

@Embeddable
public class TaskId extends ValueObject {

	private static final long serialVersionUID = 1L;

	@Column(name = "task_id", nullable = false)
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
