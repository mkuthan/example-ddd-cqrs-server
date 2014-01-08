package scrumboard.application;

import scrumboard.domain.backlog.item.BacklogItem;
import scrumboard.domain.backlog.item.BacklogItemFactory;
import scrumboard.domain.backlog.item.BacklogItemId;
import scrumboard.domain.backlog.item.BacklogItemRepository;
import scrumboard.domain.product.Product;
import scrumboard.domain.product.ProductId;
import scrumboard.domain.product.ProductRepository;
import scrumboard.domain.sprint.Sprint;
import scrumboard.domain.sprint.SprintFactory;
import scrumboard.domain.sprint.SprintId;
import scrumboard.domain.sprint.SprintRepository;

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
