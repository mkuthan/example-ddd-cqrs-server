package example.ddd.scrumboard.domain.backlog.item;

public class BacklogItemFactory {

	private BacklogItemRepository backlogItemRepository;

	public BacklogItemFactory(BacklogItemRepository backlogItemRepository) {
		this.backlogItemRepository = backlogItemRepository;
	};

	public BacklogItem create() {
		BacklogItemId id = backlogItemRepository.generateId();

		return new BacklogItem(id);
	}
}
