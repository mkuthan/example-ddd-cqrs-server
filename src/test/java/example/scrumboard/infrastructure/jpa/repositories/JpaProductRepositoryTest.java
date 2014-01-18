package example.scrumboard.infrastructure.jpa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductAssert;
import example.scrumboard.domain.product.ProductBuilder;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;
import example.scrumboard.infrastructure.jpa.AbstractJpaTest;

@JpaRepositoryTest
public class JpaProductRepositoryTest extends AbstractJpaTest {

	@Autowired
	private ProductRepository repository;

	public void shouldSaveProduct() {
		// given
		ProductId id = new ProductId("id");
		String name = "name";
		BacklogItemId backlogItemId1 = new BacklogItemId("id1");
		BacklogItemId backlogItemId2 = new BacklogItemId("id2");

		// @formatter:off
		Product product = givenProduct()
			.withId(id)
			.withName(name)
			.addBacklogItem(backlogItemId1)
			.addBacklogItem(backlogItemId2)
			.build();
		// @formatter:on

		// when
		repository.save(product);

		// @formatter:off
		thenProduct(id)
			.hasName(name)
			.hasBacklogItem(backlogItemId1, 0)
			.hasBacklogItem(backlogItemId2, 1);
		// @formatter:on
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void couldNotSaveProductWithDuplicatedName() {
		String name = "name";
		Product product1 = givenProduct().withId(new ProductId("id1")).withName(name).build();
		Product product2 = givenProduct().withId(new ProductId("id1")).withName(name).build();

		// when
		repository.save(product1);
		clear();

		repository.save(product2);
	}

	private ProductBuilder givenProduct() {
		return new ProductBuilder();
	}

	private ProductAssert thenProduct(ProductId id) {
		clear();
		return new ProductAssert(repository.load(id));
	}
}
