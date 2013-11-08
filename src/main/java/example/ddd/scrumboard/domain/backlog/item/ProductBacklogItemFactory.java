package example.ddd.scrumboard.domain.backlog.item;

public class ProductBacklogItemFactory {

	private ProductBacklogItemRepository productBacklogItemRepository;

	public ProductBacklogItemFactory(ProductBacklogItemRepository productBacklogItemRepository) {
		this.productBacklogItemRepository = productBacklogItemRepository;
	};

	public ProductBacklogItem create() {
		ProductBacklogItemId id = productBacklogItemRepository.generateId();

		return new ProductBacklogItem(id);
	}
}
