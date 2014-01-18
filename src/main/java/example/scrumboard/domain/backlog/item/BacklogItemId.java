package example.scrumboard.domain.backlog.item;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.ddd.domain.ValueObject;

@Embeddable
public class BacklogItemId extends ValueObject {

	private static final long serialVersionUID = 1L;

	@Column(name = "backlog_item_id", nullable = false)
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
