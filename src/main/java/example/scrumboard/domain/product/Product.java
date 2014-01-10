package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Ordering;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;

@Entity
public class Product extends AggregateRoot<ProductId> {

	@Column(nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(nullable = false)
	private Set<ProductBacklogItem> backlogItems;

	Product() {
	}

	Product(ProductId id, String name, Set<ProductBacklogItem> backlogItems) {
		super(id);
		this.name = requireNonNull(name);
		this.backlogItems = requireNonNull(backlogItems);
	}

	public void plan(BacklogItem backlogItem) {
		requireNonNull(backlogItem);

		BacklogItemId backlogItemId = backlogItem.getId();
		if (containsBacklogItem(backlogItemId)) {
			throw new IllegalArgumentException("Could not plan backlog item, backlog item is already planned "
					+ backlogItemId);
		}

		ProductBacklogItem productBacklogItem = new ProductBacklogItem(backlogItemId, lastPosition());
		backlogItems.add(productBacklogItem);
	}

	public void reorder(BacklogItemId backlogItemId, Integer newPosition) {
		requireNonNull(backlogItemId);
		requireNonNull(newPosition);

		Optional<ProductBacklogItem> reorderedBacklogItem = findBacklogItem(backlogItemId);
		if (!reorderedBacklogItem.isPresent()) {
			throw new IllegalArgumentException("Could not reorder backlog item, backlog item is not planned "
					+ backlogItemId);
		}

		requirePositivePosition(newPosition);

		int oldPosition = reorderedBacklogItem.get().getPosition();
		if (oldPosition == newPosition) {
			return;
		}

		Ordering<ProductBacklogItem> byPosition = Ordering.natural().onResultOf(
				new Function<ProductBacklogItem, Integer>() {

					@Override
					public Integer apply(ProductBacklogItem input) {
						return input.getPosition();
					}
				});

		List<ProductBacklogItem> sortedBacklogItems = byPosition.sortedCopy(backlogItems);
		Collections.swap(sortedBacklogItems, oldPosition, newPosition);

		List<BacklogItemId> backlogItemIds = new ArrayList<>(sortedBacklogItems.size());
		for (int position = 0; position < sortedBacklogItems.size(); position++) {
			ProductBacklogItem backlogItem = sortedBacklogItems.get(position);
			backlogItem.setPosition(newPosition);
			backlogItemIds.add(backlogItem.getId());
		}

		publish(new ProductBacklogItemReordered(getId(), backlogItemIds));
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

	private int requirePositivePosition(int position) {
		if (position < 0) {
			throw new IllegalArgumentException("Position must be positive but was " + position);
		} else {
			return position;
		}
	}

}
