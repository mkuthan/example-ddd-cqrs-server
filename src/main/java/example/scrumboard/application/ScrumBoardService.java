package example.scrumboard.application;

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
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;
import example.scrumboard.domain.sprint.SprintRepository;

@ApplicationService
public class ScrumBoardService {

	private SprintRepository sprintRepository;

	private BacklogItemFactory backlogItemFactory;

	private BacklogItemRepository backlogItemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductFactory productFactory;

	public ProductId createProduct(String name) {
		Product product = productFactory.create(name);
		productRepository.save(product);

		return product.getId();
	}

	public BacklogItemId createBacklogItem(ProductId productId) {
		BacklogItem backlogItem = backlogItemFactory.create();
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

	public void commitBacklogItem(BacklogItemId backlogItemId, SprintId sprintId) {
		Sprint sprint = sprintRepository.load(sprintId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.commitTo(sprint);

		sprint.commit(backlogItem);

		sprintRepository.save(sprint);
		backlogItemRepository.save(backlogItem);
	}

}
