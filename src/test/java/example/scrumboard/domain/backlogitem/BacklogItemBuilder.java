package example.scrumboard.domain.backlogitem;

import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductBuilder;
import example.scrumboard.domain.sprint.Sprint;

public class BacklogItemBuilder {

	private BacklogItemId id = new BacklogItemId("any id");

	private Product product = new ProductBuilder().build();

	private String name = "any name";

	private Sprint sprint;

	public BacklogItemBuilder withId(BacklogItemId id) {
		this.id = id;
		return this;
	}

	public BacklogItemBuilder withProduct(Product product) {
		this.product = product;
		return this;
	}

	public BacklogItemBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public BacklogItemBuilder commitedToSprint(Sprint sprint) {
		this.sprint = sprint;
		return this;
	}

	public BacklogItem build() {
		return new BacklogItem(id, product, name, sprint);
	}

}
