package example.ddd.scrumboard.domain.product;

import static com.google.common.collect.Lists.newArrayList;

import java.util.HashSet;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.shared.EventPublisher;
import example.ddd.scrumboard.domain.shared.EventPublisherAssert;

@Test
public class ProductTest {

	private static final ProductId ANY_ID = new ProductId("any id");

	@Mock
	private EventPublisher eventPublisher;

	@InjectMocks
	private Product product;

	private Builder builder;

	@BeforeMethod
	protected void initializeBuilder() {
		builder = new Builder().product(ANY_ID);
	}

	public void shouldReorder() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");
		BacklogItemId backlogItemId2 = new BacklogItemId("2");

		givenProduct().addBacklogItemOnPosition(backlogItemId0, 0).addBacklogItemOnPosition(backlogItemId1, 1)
				.addBacklogItemOnPosition(backlogItemId2, 2);

		whenProduct().reorder(backlogItemId0, 1);

		ProductBacklogItemReordered expectedEvent = new ProductBacklogItemReordered(product.getId(), newArrayList(
				backlogItemId1, backlogItemId0, backlogItemId2));

		thenProduct().publishedEvent(expectedEvent);
	}

	public void shouldNotReorderWhenPositionDoesNotChange() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");
		BacklogItemId backlogItemId2 = new BacklogItemId("2");

		givenProduct().addBacklogItemOnPosition(backlogItemId0, 0).addBacklogItemOnPosition(backlogItemId1, 1)
				.addBacklogItemOnPosition(backlogItemId2, 2);

		whenProduct().reorder(backlogItemId1, 1);

		thenProduct().didNotPublishEvent();
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void mustThrowExceptionWhenReorderedBacklogItemPositionIsNegative() {
		BacklogItemId backlogItemId = new BacklogItemId("any id");

		givenProduct().addBacklogItem(backlogItemId);

		whenProduct().reorder(backlogItemId, -1);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void mustThrowExceptionWhenReorderedBacklogItemDoesNotExist() {
		BacklogItemId backlogItemId = new BacklogItemId("any id");

		givenProduct().addBacklogItem(backlogItemId);

		whenProduct().reorder(new BacklogItemId("another id"), 1);
	}

	private Builder givenProduct() {
		return builder;
	}

	private Product whenProduct() {
		this.product = builder.build();

		MockitoAnnotations.initMocks(this);

		return product;
	}

	private EventPublisherAssert thenProduct() {
		return new EventPublisherAssert(eventPublisher);
	}

	public static class Builder {

		private ProductId id;

		private Set<ProductBacklogItem> backlogItems = new HashSet<>();

		public Builder product(ProductId id) {
			this.id = id;
			return this;
		}

		public Builder addBacklogItem(BacklogItemId id) {
			this.backlogItems.add(new ProductBacklogItem(id, 0));
			return this;
		}

		public Builder addBacklogItemOnPosition(BacklogItemId id, int position) {
			this.backlogItems.add(new ProductBacklogItem(id, position));
			return this;
		}

		public Product build() {
			return new Product(id, backlogItems);
		}
	}

}
