package example.scrumboard.application.bootstrap;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import example.bootstrap.domain.BootstrapEvent;
import example.bootstrap.domain.BootstrapListener;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@BootstrapListener
public class ScrumBoardBootstrap implements ApplicationListener<BootstrapEvent> {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("YYYY-MM-DD");

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (productRepository.count() == 0) {
			initialize();
		}
	}

	private void initialize() {
		ProductId productId1 = productService.createProduct("Example DDD/CQRS server");
		productService.planBacklogItem(productId1, "Write documentation");
		productService.planBacklogItem(productId1, "Add more unit tests");

		productService.scheduleSprint(productId1, "Sprint 1", DATE_TIME_FORMATTER.parseDateTime("2011-01-01").toDate(),
				DATE_TIME_FORMATTER.parseDateTime("2011-01-14").toDate());
		productService.scheduleSprint(productId1, "Sprint 2", DATE_TIME_FORMATTER.parseDateTime("2011-01-15").toDate(),
				DATE_TIME_FORMATTER.parseDateTime("2011-01-29").toDate());

		ProductId productId2 = productService.createProduct("Example DDD/CQRS client");
		productService.planBacklogItem(productId2, "Apply Twitter Bootstrap");
		productService.planBacklogItem(productId2, "Create Angular controllers");

		// no sprints

		productService.createProduct("Product with no backlog items");

		// no backlog items
		// no sprints
	}

}
