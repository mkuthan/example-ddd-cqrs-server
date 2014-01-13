package example.scrumboard.domain.release;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.ValueObject;

public class ReleaseId extends ValueObject {

	private static final long serialVersionUID = 1L;

	private String id;

	public ReleaseId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}

}
