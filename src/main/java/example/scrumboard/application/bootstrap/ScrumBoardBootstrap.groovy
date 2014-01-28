package example.scrumboard.application.bootstrap

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener

import example.bootstrap.BootstrapEvent
import example.bootstrap.BootstrapListener
import example.scrumboard.application.api.ProductService
import example.scrumboard.application.api.commands.CreateProductCommand
import example.scrumboard.application.api.commands.PlanBacklogItemCommand
import example.scrumboard.application.api.commands.ScheduleReleaseCommand
import example.scrumboard.application.api.commands.ScheduleSprintCommand
import example.scrumboard.domain.product.ProductId
import example.scrumboard.domain.product.ProductRepository
import groovy.transform.TypeChecked

@BootstrapListener
@TypeChecked
class ScrumBoardBootstrap implements ApplicationListener<BootstrapEvent> {

	static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("YYYY-MM-DD")

	@Autowired
	ProductService productService

	@Autowired
	ProductRepository productRepository

	@Override
	void onApplicationEvent(BootstrapEvent event) {
		if (productRepository.count() != 0) {
			return
		}

		/*
		 * Product1
		 */
		ProductId productId1 = productService.createProduct(
				new CreateProductCommand(productName: "Example DDD/CQRS server")
				)

		// backlog items
		productService.planBacklogItem(productId1,
				new PlanBacklogItemCommand(backlogItemStory: "Write documentation")
				)
		productService.planBacklogItem(productId1,
				new PlanBacklogItemCommand(backlogItemStory: "Add more unit tests")
				)

		// sprints
		productService.scheduleSprint(
				new ScheduleSprintCommand(productId: productId1, sprintName: "Sprint 1", sprintBeginDate: toDate("2011-01-01"), sprintEndDate: toDate("2011-01-14"))
				)
		productService.scheduleSprint(
				new ScheduleSprintCommand(productId: productId1, sprintName: "Sprint 2", sprintBeginDate: toDate("2011-01-15"), sprintEndDate: toDate("2011-01-29"))
				)

		// releases
		productService.scheduleRelease(
				new ScheduleReleaseCommand(productId: productId1, releaseName: "Release 1", releaseDate: toDate("2011-01-14"))
				)

		productService.scheduleRelease(
				new ScheduleReleaseCommand(productId: productId1, releaseName: "Release 2", releaseDate: toDate("2011-01-29"))
				)


		/*
		 * Product2
		 */
		ProductId productId2 = productService.createProduct(
				new CreateProductCommand(productName: "Example DDD/CQRS client")
				)

		// backlog items
		productService.planBacklogItem(productId2,
				new PlanBacklogItemCommand(backlogItemStory: "Apply Twitter Bootstrap")
				)
		// backlog items
		productService.planBacklogItem(productId2,
				new PlanBacklogItemCommand(backlogItemStory: "Create Angular controllers")
				)

		// no sprints

		/*
		 *  Product3
		 */
		productService.createProduct(
				new CreateProductCommand(productName: "Product with no backlog items")
				)

		// no backlog items

		// no sprints
	}

	Date toDate(String date) {
		DATE_TIME_FORMATTER.parseDateTime(date).toDate()
	}
}
