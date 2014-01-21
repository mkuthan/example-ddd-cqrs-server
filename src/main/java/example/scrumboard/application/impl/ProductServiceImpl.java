package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.application.api.commands.CreateProductCommand;
import example.scrumboard.application.api.commands.PlanBacklogItemCommand;
import example.scrumboard.application.api.commands.ReorderBacklogItemsCommand;
import example.scrumboard.application.api.commands.ScheduleReleaseCommand;
import example.scrumboard.application.api.commands.ScheduleSprintCommand;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemFactory;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.backlogitem.BacklogItemRepository;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductFactory;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;
import example.scrumboard.domain.release.Release;
import example.scrumboard.domain.release.ReleaseFactory;
import example.scrumboard.domain.release.ReleaseId;
import example.scrumboard.domain.release.ReleaseRepository;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintFactory;
import example.scrumboard.domain.sprint.SprintId;
import example.scrumboard.domain.sprint.SprintRepository;

@ApplicationService
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductFactory productFactory;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BacklogItemFactory backlogItemFactory;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	@Autowired
	private ReleaseFactory releaseFactory;

	@Autowired
	private ReleaseRepository releaseRepository;

	@Autowired
	private SprintFactory sprintFactory;

	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public ProductId createProduct(CreateProductCommand command) {
		Product product = productFactory.create(command.getProductName());
		productRepository.save(product);

		return product.getId();
	}

	@Override
	public BacklogItemId planBacklogItem(ProductId productId, PlanBacklogItemCommand command) {
		Product product = productRepository.load(productId);

		BacklogItem backlogItem = backlogItemFactory.create(product, command.getBacklogItemStory());
		backlogItemRepository.save(backlogItem);

		product.planBacklogItem(backlogItem);
		productRepository.save(product);

		return backlogItem.getId();
	}

	@Override
	public void reorderBacklogItems(ReorderBacklogItemsCommand command) {
		Product product = productRepository.load(command.getProductId());
		product.reorderBacklogItems(command.getBacklogItemIds());

		productRepository.save(product);
	}

	@Override
	public ReleaseId scheduleRelease(ScheduleReleaseCommand command) {
		Product product = productRepository.load(command.getProductId());

		Release release = releaseFactory.create(product, command.getReleaseName(), command.getReleaseDate());
		releaseRepository.save(release);

		return release.getId();
	}

	@Override
	public SprintId scheduleSprint(ScheduleSprintCommand command) {
		Product product = productRepository.load(command.getProductId());

		Sprint sprint = sprintFactory.create(product, command.getSprintName(), command.getSprintBeginDate(),
				command.getSprintEndDate());
		sprintRepository.save(sprint);

		return sprint.getId();
	}

}
