package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;
import example.scrumboard.domain.backlog.item.BacklogItemId;

public class ProductBacklogItem {

	private BacklogItemId id;

	private Integer position;

	ProductBacklogItem(BacklogItemId id, Integer position) {
		this.id = requireNonNull(id);
		this.position = requireNonNull(position);
	}

	public BacklogItemId getId() {
		return id;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
}
