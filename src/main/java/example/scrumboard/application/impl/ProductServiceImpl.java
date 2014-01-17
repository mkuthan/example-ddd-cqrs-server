package example.scrumboard.application.impl;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemFactory;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductFactory;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;
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
	public BacklogItemId createProductBacklogItem(ProductId productId, String backlogItemName) {
		BacklogItem backlogItem = backlogItemFactory.create(backlogItemName);
		backlogItemRepository.save(backlogItem);

		Product product = productRepository.load(productId);
		product.assign(backlogItem);
		productRepository.save(product);

		return backlogItem.getId();
	}

	@Override
	public void reorderProductBacklogItems(ProductId productId, BacklogItemId... backlogItemIds) {
		Product product = productRepository.load(productId);
		product.reorder(backlogItemIds);

		productRepository.save(product);
	}

	public SprintId scheduleSprint(ProductId productId, String sprintName, Date sprintBeginDate, Date sprintEndDate) {
		Sprint sprint = sprintFactory.create(sprintName, sprintBeginDate, sprintEndDate);
		sprintRepository.save(sprint);

		Product product = productRepository.load(productId);
		product.plan(sprint);
		productRepository.save(product);

		
		return sprint.getId();
	}

}
