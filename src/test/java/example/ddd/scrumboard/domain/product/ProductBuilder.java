package example.ddd.scrumboard.domain.product;

import java.util.HashSet;
import java.util.Set;

import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.shared.EventPublisher;

public class ProductBuilder {

	private EventPublisher eventPublisher;

	private ProductId id;

	private Set<ProductBacklogItem> backlogItems = new HashSet<>();

	public ProductBuilder product(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		return this;
	}

	public ProductBuilder withId(ProductId id) {
		this.id = id;
		return this;
	}

	public ProductBuilder addBacklogItemOnPosition(BacklogItemId id, int position) {
		this.backlogItems.add(new ProductBacklogItem(id, position));
		return this;
	}

	public Product build() {
		return new Product(id, backlogItems, eventPublisher);
	}
}
