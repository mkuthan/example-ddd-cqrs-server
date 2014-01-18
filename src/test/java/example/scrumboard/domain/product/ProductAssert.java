package example.scrumboard.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import com.google.common.base.Optional;

import example.scrumboard.domain.backlogitem.BacklogItemId;

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
		assertThat(backlogItem.orNull()).overridingErrorMessage("Backlog item %s not found", backlogItemId).isNotNull();

		int actualPosition = backlogItem.get().getPosition();
		assertThat(actualPosition).overridingErrorMessage(
				"Expected backlog item '%s' at position %s, but was at position %s", backlogItemId, position,
				actualPosition).isEqualTo(position);

		return this;
	}

}
