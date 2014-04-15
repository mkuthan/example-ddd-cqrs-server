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

import com.google.common.collect.Iterables;

import example.ddd.AggregateRoot;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;

@Entity
public class Product extends AggregateRoot<ProductId> {

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
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
		backlogItem.checkProduct(getId());

		BacklogItemId backlogItemId = backlogItem.getId();
		if (Iterables.any(backlogItems, ProductBacklogItem.hasId(backlogItemId))) {
			throw new IllegalArgumentException("Backlog item is already planned " + backlogItemId);
		}

		int position = backlogItems.size();
		backlogItems.add(new ProductBacklogItem(backlogItemId, position));

		register(new BacklogItemPlannedEvent(getId(), backlogItemId));
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

}
