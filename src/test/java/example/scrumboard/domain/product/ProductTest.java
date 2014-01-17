package example.scrumboard.domain.product;

import static com.google.common.collect.Lists.newArrayList;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.ddd.domain.EventPublisher;
import example.ddd.domain.EventPublisherAssert;
import example.scrumboard.domain.backlog.item.BacklogItemId;

@Test
public class ProductTest {

	private static final ProductId ANY_ID = new ProductId("any id");
	private static final String ANY_NAME = "any name";

	@Mock
	private EventPublisher eventPublisher;

	@InjectMocks
	private Product product;

	private ProductBuilder productBuilder;

	@BeforeMethod
	protected void initializeBuilder() {
		productBuilder = new ProductBuilder().withId(ANY_ID).withName(ANY_NAME);
	}

	public void shouldReorder() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");
		BacklogItemId backlogItemId2 = new BacklogItemId("2");

		// @formatter:off
		givenProduct()
			.addBacklogItem(backlogItemId0)
			.addBacklogItem(backlogItemId1)
			.addBacklogItem(backlogItemId2);
		// @formatter:on

		whenProduct().reorder(backlogItemId0, 1);

		ProductBacklogItemReordered expectedEvent = new ProductBacklogItemReordered(product.getId(), newArrayList(
				backlogItemId1, backlogItemId0, backlogItemId2));

		thenProduct().eventPublished(expectedEvent);
	}

	public void shouldNotReorderWhenPositionDoesNotChange() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");
		BacklogItemId backlogItemId2 = new BacklogItemId("2");

		// @formatter:off
		givenProduct()
			.addBacklogItem(backlogItemId0)
			.addBacklogItem(backlogItemId1)
			.addBacklogItem(backlogItemId2);
		// @formatter:on

		whenProduct().reorder(backlogItemId1, 1);

		thenProduct().eventWasNotPublished();
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

	private ProductBuilder givenProduct() {
		return productBuilder;
	}

	private Product whenProduct() {
		this.product = productBuilder.build();

		MockitoAnnotations.initMocks(this);

		return product;
	}

	private EventPublisherAssert thenProduct() {
		return new EventPublisherAssert(eventPublisher);
	}

}
