package example.scrumboard.application.services;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemFactory;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductFactory;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@ApplicationService
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductFactory productFactory;

	@Autowired
	private BacklogItemFactory backlogItemFactory;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	public ProductId createProduct(String name) {
		Product product = productFactory.create(name);
		productRepository.save(product);

		return product.getId();
	}

	public BacklogItemId createBacklogItem(ProductId productId, String name) {
		BacklogItem backlogItem = backlogItemFactory.create(name);
		backlogItemRepository.save(backlogItem);

		Product product = productRepository.load(productId);
		product.plan(backlogItem);
		productRepository.save(product);

		return backlogItem.getId();
	}

	public void reorderProductBacklogItem(ProductId productId, BacklogItemId backlogItemId, int newPosition) {
		Product product = productRepository.load(productId);
		product.reorder(backlogItemId, newPosition);

		productRepository.save(product);
	}

}
