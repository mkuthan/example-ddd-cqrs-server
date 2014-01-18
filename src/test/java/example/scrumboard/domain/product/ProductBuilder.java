package example.scrumboard.domain.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import example.scrumboard.domain.backlogitem.BacklogItemId;

public class ProductBuilder {

	private ProductId id = new ProductId("any id");

	private String name = "any name";

	private List<ProductBacklogItem> backlogItems = new ArrayList<>();

	public ProductBuilder withId(ProductId id) {
		this.id = id;
		return this;
	}

	public ProductBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public ProductBuilder addBacklogItem(BacklogItemId id) {
		int position = backlogItems.size();
		backlogItems.add(new ProductBacklogItem(id, position));
		return this;
	}

	public Product build() {
		return new Product(id, name, new HashSet<>(backlogItems));
	}
}