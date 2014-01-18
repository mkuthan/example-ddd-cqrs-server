package example.scrumboard.domain.product;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static example.ddd.domain.DddAssertions.thenEvent;
import static example.scrumboard.domain.ScrumBoardBuilders.givenBacklogItem;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import example.ddd.domain.EventPublisher;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;

@Test
public class ProductTest {

	@Mock
	private EventPublisher eventPublisher;

	@InjectMocks
	private Product product;

	private ProductBuilder productBuilder;

	public void shouldAssignFirstBacklogItem() {
		BacklogItemId backlogItemId = new BacklogItemId("id");
		BacklogItem backlogItem = givenBacklogItem().withId(backlogItemId).build();

		givenProduct();

		whenProduct().planBacklogItem(backlogItem);

		thenProduct().hasBacklogItem(backlogItemId, 0);
		thenEvent(eventPublisher).published(new BacklogItemAssignedToProductEvent(product.getId(), backlogItemId));
	}

	public void shouldAssignSecondBacklogItem() {
		BacklogItemId backlogItemId = new BacklogItemId("id");
		BacklogItem backlogItem = givenBacklogItem().withId(backlogItemId).build();

		givenProduct().addBacklogItem(new BacklogItemId("existing id"));

		whenProduct().planBacklogItem(backlogItem);

		thenProduct().hasBacklogItem(backlogItemId, 1);
		thenEvent(eventPublisher).published(new BacklogItemAssignedToProductEvent(product.getId(), backlogItemId));
	}

	public void shouldNotAssignExistingBacklogItem() {
		BacklogItemId backlogItemId = new BacklogItemId("id");
		BacklogItem backlogItem = givenBacklogItem().withId(backlogItemId).build();

		givenProduct().addBacklogItem(backlogItemId);

		catchException(whenProduct()).planBacklogItem(backlogItem);

		assertThat(caughtException()).isInstanceOf(IllegalArgumentException.class);
		thenEvent(eventPublisher).notPublished();
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

		whenProduct().reorderBacklogItems(backlogItemId2, backlogItemId1, backlogItemId0);

		// @formatter:off
		thenProduct()
			.hasBacklogItem(backlogItemId0, 2)
			.hasBacklogItem(backlogItemId1, 1)
			.hasBacklogItem(backlogItemId2, 0);
		// @formatter:on

		thenEvent(eventPublisher).notPublished();
	}

	public void shouldNotReorderWhenNoChanges() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");
		BacklogItemId backlogItemId2 = new BacklogItemId("2");

		// @formatter:off
		givenProduct()
			.addBacklogItem(backlogItemId0)
			.addBacklogItem(backlogItemId1)
			.addBacklogItem(backlogItemId2);
		// @formatter:on

		whenProduct().reorderBacklogItems(backlogItemId0, backlogItemId1, backlogItemId2);

		// @formatter:off
		thenProduct()
			.hasBacklogItem(backlogItemId0, 0)
			.hasBacklogItem(backlogItemId1, 1)
			.hasBacklogItem(backlogItemId2, 2);
		// @formatter:on

		thenEvent(eventPublisher).notPublished();
	}

	public void shouldNotReorderNonExistingBacklogItem() {
		BacklogItemId backlogItemId0 = new BacklogItemId("0");
		BacklogItemId backlogItemId1 = new BacklogItemId("1");

		givenProduct().addBacklogItem(backlogItemId0);

		catchException(whenProduct()).reorderBacklogItems(backlogItemId1);
		assertThat(caughtException()).isInstanceOf(IllegalArgumentException.class);

		thenEvent(eventPublisher).notPublished();
	}

	private ProductBuilder givenProduct() {
		productBuilder = new ProductBuilder();
		return productBuilder;
	}

	private Product whenProduct() {
		this.product = productBuilder.build();

		MockitoAnnotations.initMocks(this);

		return product;
	}

	private ProductAssert thenProduct() {
		return new ProductAssert(product);
	}

}
