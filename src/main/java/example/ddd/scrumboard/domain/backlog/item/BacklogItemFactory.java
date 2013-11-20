package example.ddd.scrumboard.domain.backlog.item;

import example.ddd.scrumboard.domain.shared.EventPublisher;

public class BacklogItemFactory {

	private EventPublisher eventPublisher;

	private BacklogItemRepository backlogItemRepository;

	public BacklogItemFactory(EventPublisher eventPublisher, BacklogItemRepository backlogItemRepository) {
		this.eventPublisher = eventPublisher;
		this.backlogItemRepository = backlogItemRepository;
	};

	public BacklogItem create() {
		BacklogItemId id = backlogItemRepository.generateId();

		return new BacklogItem(id, eventPublisher);
	}
}
