package example.scrumboard.application.bootstrap

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener

import example.bootstrap.domain.BootstrapEvent
import example.bootstrap.domain.BootstrapListener
import example.scrumboard.application.api.CreateProductCommand
import example.scrumboard.application.api.ProductService
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
		ProductId productId1 = productService.createProduct(new CreateProductCommand(productName: "Example DDD/CQRS server"))

		productService.planBacklogItem(productId1, "Write documentation")
		productService.planBacklogItem(productId1, "Add more unit tests")

		productService.scheduleSprint(productId1, "Sprint 1", toDate("2011-01-01"), toDate("2011-01-14"))
		productService.scheduleSprint(productId1, "Sprint 2", toDate("2011-01-15"),	toDate("2011-01-29"))


		/*
		 * Product2
		 */
		ProductId productId2 = productService.createProduct(new CreateProductCommand(productName: "Example DDD/CQRS client"))
		productService.planBacklogItem(productId2, "Apply Twitter Bootstrap")
		productService.planBacklogItem(productId2, "Create Angular controllers")

		// no sprints

		/*
		 *  Product3
		 */
		productService.createProduct(new CreateProductCommand(productName: "Product with no backlog items"))

		// no backlog items

		// no sprints
	}

	Date toDate(String date) {
		DATE_TIME_FORMATTER.parseDateTime(date).toDate()
	}
}
