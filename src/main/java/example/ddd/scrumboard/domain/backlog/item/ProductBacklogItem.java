package example.ddd.scrumboard.domain.backlog.item;

import example.ddd.scrumboard.domain.shared.AggregateEntity;

public class ProductBacklogItem extends AggregateEntity {

	private ProductBacklogItemId id;

	protected ProductBacklogItem(ProductBacklogItemId id) {
		this.id = id;
	}

	public ProductBacklogItemId getId() {
		return id;
	}

}
