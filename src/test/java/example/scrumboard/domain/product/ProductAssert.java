package example.scrumboard.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import com.google.common.base.Optional;

import example.scrumboard.domain.backlog.item.BacklogItemId;

public class ProductAssert extends AbstractAssert<ProductAssert, Product> {

	public ProductAssert(Product actual) {
		super(actual, ProductAssert.class);
	}

	public ProductAssert hasName(String name) {
		assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ProductAssert hasBacklogItem(BacklogItemId backlogItemId, int position) {
		Optional<ProductBacklogItem> backlogItem = actual.findBacklogItem(backlogItemId);
		assertThat(backlogItem.orNull()).isNotNull();
		assertThat(backlogItem.get().getPosition()).isEqualTo(position);

		return this;
	}

}
