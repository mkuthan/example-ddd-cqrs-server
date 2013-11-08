package example.ddd.scrumboard.application;

import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItem;
import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItemId;
import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItemRepository;
import example.ddd.scrumboard.domain.sprint.Sprint;
import example.ddd.scrumboard.domain.sprint.SprintFactory;
import example.ddd.scrumboard.domain.sprint.SprintId;
import example.ddd.scrumboard.domain.sprint.SprintRepository;

public class SprintService {

	private SprintFactory sprintFactory;

	private SprintRepository sprintRepository;

	private ProductBacklogItemRepository productBacklogItemRepository;

	public SprintId create() {
		Sprint sprint = sprintFactory.create();

		sprintRepository.save(sprint);
		return sprint.getId();
	}

	public void commitProductBacklogItem(SprintId id, ProductBacklogItemId productBacklogItemId) {
		Sprint sprint = sprintRepository.load(id);
		ProductBacklogItem productBacklogItem = productBacklogItemRepository.load(productBacklogItemId);

		sprint.commitProductBacklogItem(productBacklogItem);
	}

}
