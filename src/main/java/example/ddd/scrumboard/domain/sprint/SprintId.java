package example.ddd.scrumboard.domain.sprint;

import java.util.Objects;

public class SprintId {

	private String id;

	public SprintId(String id) {
		this.id = Objects.requireNonNull(id);
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public boolean equals(Object that) {
		return Objects.equals(this, that);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this);
	}

}
