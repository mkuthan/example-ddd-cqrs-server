package example.scrumboard.application.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintFactory;
import example.scrumboard.domain.sprint.SprintId;
import example.scrumboard.domain.sprint.SprintRepository;

@ApplicationService
public class SprintService {

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private SprintFactory sprintFactory;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	public SprintId scheduleSprint(ProductId productId, String name, Date beginDate, Date endDate) {
		Sprint sprint = sprintFactory.create(name, beginDate, endDate);
		sprintRepository.save(sprint);

		Product product = productRepository.load(productId);
		product.plan(sprint);
		productRepository.save(product);

		return sprint.getId();
	}

	public void commitBacklogItem(SprintId sprintId, BacklogItemId backlogItemId) {
		Sprint sprint = sprintRepository.load(sprintId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.commitTo(sprint);

		sprint.commit(backlogItem);

		backlogItemRepository.save(backlogItem);
		sprintRepository.save(sprint);
	}

	public void uncommitBacklogItem(SprintId sprintId, BacklogItemId backlogItemId) {
		Sprint sprint = sprintRepository.load(sprintId);

		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);
		backlogItem.uncommitFrom(sprint);

		sprint.uncommit(backlogItem);

		backlogItemRepository.save(backlogItem);
		sprintRepository.save(sprint);
	}

	public void captureRetrospective(SprintId sprintId, String retrospective) {
		Sprint sprint = sprintRepository.load(sprintId);

		sprint.captureRetrospective(retrospective);

		sprintRepository.save(sprint);
	}

}
