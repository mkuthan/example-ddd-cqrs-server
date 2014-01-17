package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.Event;
import example.scrumboard.domain.backlog.item.BacklogItemId;

public class BacklogItemAssignedToProductEvent extends Event {

	private static final long serialVersionUID = 1L;

	private ProductId productId;

	private BacklogItemId backlogItemId;

	public BacklogItemAssignedToProductEvent(ProductId productId, BacklogItemId backlogItemId) {
		this.productId = requireNonNull(productId);
		this.backlogItemId = requireNonNull(backlogItemId);
	}

	public ProductId getProductId() {
		return productId;
	}

	public BacklogItemId getBacklogItemId() {
		return backlogItemId;
	}
	
}
