package example.ddd.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.shared.DomainEvent;

public class ProductBacklogItemReordered implements DomainEvent {

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		ProductBacklogItemReordered that = (ProductBacklogItemReordered) obj;
		return Objects.equals(this.productId, that.productId)
				&& Objects.equals(this.backlogItemIds, that.backlogItemIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.productId, this.backlogItemIds);
	}

}
