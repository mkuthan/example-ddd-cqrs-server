package example.ddd.scrumboard.application;

import org.joda.time.Period;

import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItem;
import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItemFactory;
import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItemId;
import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItemRepository;
import example.ddd.scrumboard.domain.backlog.task.StoryPoints;
import example.ddd.scrumboard.domain.backlog.task.TaskId;

public class ProductBacklogService {

	private ProductBacklogItemFactory productBacklogItemFactory;

	private ProductBacklogItemRepository productBacklogItemRepository;
	
	public ProductBacklogItemId createItem() {
		ProductBacklogItem productBacklogItem = productBacklogItemFactory.create();

		productBacklogItemRepository.save(productBacklogItem);
		return productBacklogItem.getId();
	}

	public void estimateItem(ProductBacklogItemId itemId, StoryPoints estimation) {
	}

	public void reorderItem(ProductBacklogItemId itemId) {
	}

	public TaskId splitItem(ProductBacklogItemId itemId) {
		return null;
	}

	public void estimateTaskRemaining(TaskId taskId, Period remaining) {
	}

}
