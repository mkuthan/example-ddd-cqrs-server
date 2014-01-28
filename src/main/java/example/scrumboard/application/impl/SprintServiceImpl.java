package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.ApplicationService;
import example.scrumboard.application.api.SprintService;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.backlogitem.BacklogItemRepository;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;
import example.scrumboard.domain.sprint.SprintRepository;

@ApplicationService
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	@Override
	public void commitBacklogItem(SprintId sprintId, BacklogItemId backlogItemId) {
		Sprint sprint = sprintRepository.load(sprintId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.commitToSprint(sprint);

		sprint.commitBacklogItem(backlogItem);

		backlogItemRepository.save(backlogItem);
		sprintRepository.save(sprint);
	}

	@Override
	public void uncommitBacklogItem(SprintId sprintId, BacklogItemId backlogItemId) {
		Sprint sprint = sprintRepository.load(sprintId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.uncommitFromSprint(sprint);

		sprint.uncommitBacklogItem(backlogItem);

		backlogItemRepository.save(backlogItem);
		sprintRepository.save(sprint);
	}

	@Override
	public void captureRetrospective(SprintId sprintId, String retrospective) {
		// TODO Auto-generated method stub
	}

}
