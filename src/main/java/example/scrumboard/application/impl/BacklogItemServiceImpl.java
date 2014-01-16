package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemFactory;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemPriority;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.backlog.item.StoryPoints;

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
