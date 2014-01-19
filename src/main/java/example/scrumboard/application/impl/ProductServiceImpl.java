package example.scrumboard.application.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.application.api.ProductService;
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
	public ProductId createProduct(String productName) {
		Product product = productFactory.create(productName);
		productRepository.save(product);

		return product.getId();
	}

	@Override
	public BacklogItemId planBacklogItem(ProductId productId, String backlogItemStory) {
		Product product = productRepository.load(productId);

		BacklogItem backlogItem = backlogItemFactory.create(product, backlogItemStory);
		backlogItemRepository.save(backlogItem);

		product.planBacklogItem(backlogItem);
		productRepository.save(product);

		return backlogItem.getId();
	}

	@Override
	public void reorderBacklogItems(ProductId productId, BacklogItemId... backlogItemIds) {
		Product product = productRepository.load(productId);
		product.reorderBacklogItems(backlogItemIds);

		productRepository.save(product);
	}

	@Override
	public ReleaseId scheduleRelease(ProductId productId, String releaseName, Date releaseDate) {
		Product product = productRepository.load(productId);

		Release release = releaseFactory.create(product, releaseName, releaseDate);
		releaseRepository.save(release);

		return release.getId();
	}

	@Override
	public SprintId scheduleSprint(ProductId productId, String sprintName, Date sprintBeginDate, Date sprintEndDate) {
		Product product = productRepository.load(productId);

		Sprint sprint = sprintFactory.create(product, sprintName, sprintBeginDate, sprintEndDate);
		sprintRepository.save(sprint);

		return sprint.getId();
	}

}
