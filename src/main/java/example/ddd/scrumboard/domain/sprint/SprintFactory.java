package example.ddd.scrumboard.domain.sprint;

import example.ddd.scrumboard.domain.shared.EventPublisher;

public class SprintFactory {

	private EventPublisher eventPublisher;

	private SprintRepository sprintRepository;

	public SprintFactory(EventPublisher eventPublisher, SprintRepository repository) {
		this.eventPublisher = eventPublisher;
		this.sprintRepository = repository;
	};

	public Sprint create() {
		SprintId id = sprintRepository.generateId();

		return new Sprint(id, eventPublisher);
	}
}
