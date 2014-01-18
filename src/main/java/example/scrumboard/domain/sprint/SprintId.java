package example.scrumboard.domain.sprint;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.ddd.domain.ValueObject;

@Embeddable
public class SprintId extends ValueObject {

	private static final long serialVersionUID = 1L;

	@Column(name = "sprint_id", nullable = false)
	private String id;

	SprintId() {
	}

	public SprintId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
