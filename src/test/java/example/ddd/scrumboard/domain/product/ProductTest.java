package example.ddd.scrumboard.domain.product;

import static com.google.common.collect.Lists.newArrayList;

import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.shared.EventPublisher;

@Test
public class ProductTest {

	private Product product;

	private ProductBuilder builder;

	private EventPublisher eventPublisher;

	@BeforeTest
	protected void initializeBuilder() {
		eventPublisher = Mockito.mock(EventPublisher.class);
		builder = new ProductBuilder().product(eventPublisher).withId(new ProductId("any id"));
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
		thenProduct().eventPublished(expectedEvent);
	}

	public void shouldNotReorder() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");
		BacklogItemId backlogItemId2 = new BacklogItemId("2");

		givenProduct().addBacklogItemOnPosition(backlogItemId0, 0).addBacklogItemOnPosition(backlogItemId1, 1)
				.addBacklogItemOnPosition(backlogItemId2, 2);

		whenProduct().reorder(backlogItemId1, 1);

		thenProduct().eventNotPublished();
	}

	private ProductBuilder givenProduct() {
		return builder;
	}

	private Product whenProduct() {
		this.product = builder.build();
		return product;
	}

	private ProductAssert thenProduct() {
		return new ProductAssert(product);
	}

}
