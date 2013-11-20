package example.ddd.scrumboard.domain.product;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.fest.assertions.GenericAssert;

public class ProductAssert extends GenericAssert<ProductAssert, Product> {

	public ProductAssert(Product actual) {
		super(ProductAssert.class, actual);
	}

	public ProductAssert eventPublished(ProductBacklogItemReordered event) {
		verify(actual.getEventPublisher()).publish(eq(event));
		return this;
	}

	public ProductAssert eventNotPublished() {
		verifyNoMoreInteractions(actual.getEventPublisher());
		return this;
	}

}
