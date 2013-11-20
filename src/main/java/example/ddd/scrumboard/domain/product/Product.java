package example.ddd.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;

import example.ddd.scrumboard.domain.backlog.item.BacklogItem;
import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.shared.AggregateRoot;
import example.ddd.scrumboard.domain.shared.EventPublisher;

public class Product extends AggregateRoot<ProductId> {

	private Set<ProductBacklogItem> backlogItems;

	public void plan(BacklogItem backlogItem) {
		BacklogItemId backlogItemId = backlogItem.getId();
		if (containsBacklogItem(backlogItemId)) {
			throw new IllegalArgumentException("Could not plan backlog item, backlog item is already planned "
					+ backlogItemId);
		}

		ProductBacklogItem productBacklogItem = new ProductBacklogItem(backlogItemId, lastPosition());
		backlogItems.add(productBacklogItem);
	}

	public void reorder(BacklogItemId backlogItemId, int newPosition) {
		Optional<ProductBacklogItem> reorderedBacklogItem = findBacklogItem(backlogItemId);
		if (!reorderedBacklogItem.isPresent()) {
			throw new IllegalArgumentException("Could not reorder backlog item, backlog item is not planned "
					+ backlogItemId);
		}
		int oldPosition = reorderedBacklogItem.get().getPosition();
		if (oldPosition == newPosition) {
			return;
		}

		List<ProductBacklogItem> sortedBacklogItems = ProductBacklogItem.byPosition().sortedCopy(backlogItems);
		Collections.swap(sortedBacklogItems, oldPosition, newPosition);

		List<BacklogItemId> backlogItemIds = new ArrayList<>(sortedBacklogItems.size());
		for (int position = 0; position < sortedBacklogItems.size(); position++) {
			ProductBacklogItem backlogItem = sortedBacklogItems.get(position);
			backlogItem.reorder(newPosition);
			backlogItemIds.add(backlogItem.getId());
		}

		getEventPublisher().publish(new ProductBacklogItemReordered(getId(), backlogItemIds));
	}

	Product(ProductId id, Set<ProductBacklogItem> backlogItems, EventPublisher eventPublisher) {
		super(id, eventPublisher);
		this.backlogItems = requireNonNull(backlogItems);
	}

	private int lastPosition() {
		return backlogItems.size();
	}

	private Optional<ProductBacklogItem> findBacklogItem(BacklogItemId backlogItemId) {
		for (ProductBacklogItem backlogItem : backlogItems) {
			if (backlogItem.getId().equals(backlogItemId)) {
				return Optional.of(backlogItem);
			}
		}
		return Optional.absent();
	}

	private boolean containsBacklogItem(BacklogItemId backlogItemId) {
		return findBacklogItem(backlogItemId).isPresent();
	}

}
