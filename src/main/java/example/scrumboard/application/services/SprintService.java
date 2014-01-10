package example.scrumboard.application.services;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;
import example.scrumboard.domain.sprint.SprintRepository;

@ApplicationService
public class SprintService {

	private SprintRepository sprintRepository;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	public void commitBacklogItem(BacklogItemId backlogItemId, SprintId sprintId) {
		Sprint sprint = sprintRepository.load(sprintId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.commitTo(sprint);

		sprint.commit(backlogItem);

		sprintRepository.save(sprint);
		backlogItemRepository.save(backlogItem);
	}

}
