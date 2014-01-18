package example.scrumboard.domain.product;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;

@Entity
public class Product extends AggregateRoot<ProductId> {

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id", nullable = false)
	private Set<ProductBacklogItem> backlogItems;

	Product() {
	}

	Product(ProductId id, String name, Set<ProductBacklogItem> backlogItems) {
		super(id);
		this.name = requireNonNull(name);
		this.backlogItems = requireNonNull(backlogItems);
	}

	public void planBacklogItem(BacklogItem backlogItem) {
		requireNonNull(backlogItem);

		BacklogItemId backlogItemId = backlogItem.getId();
		if (containsBacklogItem(backlogItemId)) {
			throw new IllegalArgumentException("Could not assign backlog item, backlog item is already assigned "
					+ backlogItemId);
		}

		int position = backlogItems.size();
		backlogItems.add(new ProductBacklogItem(backlogItemId, position));

		publish(new BacklogItemAssignedToProductEvent(getId(), backlogItemId));
	}

	public void reorderBacklogItems(List<BacklogItemId> backlogItemIds) {
		requireNonNull(backlogItemIds);

		for (ProductBacklogItem backlogItem : backlogItems) {
			BacklogItemId backlogItemId = backlogItem.getId();

			int newPosition = backlogItemIds.indexOf(backlogItemId);
			if (newPosition == -1) {
				throw new IllegalArgumentException("Backlog item not found " + backlogItemId);
			}

			backlogItem.setPosition(newPosition);
		}

	}

	public void reorderBacklogItems(BacklogItemId... backlogItemIds) {
		requireNonNull(backlogItemIds);
		reorderBacklogItems(newArrayList(backlogItemIds));
	}

	String getName() {
		return name;
	}

	Set<ProductBacklogItem> getBacklogItems() {
		return backlogItems;
	}

	Optional<ProductBacklogItem> findBacklogItem(BacklogItemId backlogItemId) {
		return FluentIterable.from(backlogItems).firstMatch(ProductBacklogItem.hasId(backlogItemId));
	}

	boolean containsBacklogItem(BacklogItemId backlogItemId) {
		return findBacklogItem(backlogItemId).isPresent();
	}

}
