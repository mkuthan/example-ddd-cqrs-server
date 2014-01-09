package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

import example.ddd.domain.Event;
import example.scrumboard.domain.backlog.item.BacklogItemId;

public class ProductBacklogItemReordered extends Event {

	private static final long serialVersionUID = 1L;

	private ProductId productId;

	private List<BacklogItemId> backlogItemIds;

	public ProductBacklogItemReordered(ProductId productId, List<BacklogItemId> backlogItemIds) {
		this.productId = requireNonNull(productId);
		this.backlogItemIds = requireNonNull(backlogItemIds);
	}

	public ProductId getProductId() {
		return productId;
	}

	public List<BacklogItemId> getBacklogItemIds() {
		return Collections.unmodifiableList(backlogItemIds);
	}

}
