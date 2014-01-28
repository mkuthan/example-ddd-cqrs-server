package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.ApplicationService;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemFactory;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.backlogitem.BacklogItemPriority;
import example.scrumboard.domain.backlogitem.BacklogItemRepository;
import example.scrumboard.domain.backlogitem.StoryPoints;

@ApplicationService
public class BacklogItemServiceImpl {

	@Autowired
	private BacklogItemFactory backlogItemFactory;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

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
