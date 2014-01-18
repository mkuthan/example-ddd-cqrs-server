package example.scrumboard.domain.release;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.ddd.domain.ValueObject;

@Embeddable
public class ReleaseId extends ValueObject {

	private static final long serialVersionUID = 1L;

	@Column(name = "release_id", nullable = false)
	private String id;

	ReleaseId() {
	}

	public ReleaseId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
