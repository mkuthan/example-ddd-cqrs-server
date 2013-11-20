package example.ddd.scrumboard.application;

import example.ddd.scrumboard.domain.backlog.item.BacklogItem;
import example.ddd.scrumboard.domain.backlog.item.BacklogItemFactory;
import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.ddd.scrumboard.domain.product.Product;
import example.ddd.scrumboard.domain.product.ProductId;
import example.ddd.scrumboard.domain.product.ProductRepository;
import example.ddd.scrumboard.domain.sprint.Sprint;
import example.ddd.scrumboard.domain.sprint.SprintFactory;
import example.ddd.scrumboard.domain.sprint.SprintId;
import example.ddd.scrumboard.domain.sprint.SprintRepository;

public class ScrumBoardService {

	private SprintFactory sprintFactory;

	private SprintRepository sprintRepository;

	private BacklogItemFactory backlogItemFactory;

	private BacklogItemRepository backlogItemRepository;

	private ProductRepository productRepository;

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
