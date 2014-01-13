package example.scrumboard.application.services;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemFactory;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemPriority;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.backlog.item.StoryPoints;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@ApplicationService
public class BacklogItemService {

	@Autowired
	private BacklogItemFactory backlogItemFactory;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	@Autowired
	private ProductRepository productRepository;

	public BacklogItemId createBacklogItem(ProductId productId, String name) {
		BacklogItem backlogItem = backlogItemFactory.create(name);
		backlogItemRepository.save(backlogItem);

		Product product = productRepository.load(productId);
		product.plan(backlogItem);
		productRepository.save(product);

		return backlogItem.getId();
	}

	public void assignStoryPoints(BacklogItemId backlogItemId, StoryPoints storyPoints) {
		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);

		backlogItem.assignStoryPoints(storyPoints);

		backlogItemRepository.save(backlogItem);
	}

	public void assignPriority(BacklogItemId backlogItemId, BacklogItemPriority priority) {
		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);

		backlogItem.assignPriority(priority);

		backlogItemRepository.save(backlogItem);
	}

}
