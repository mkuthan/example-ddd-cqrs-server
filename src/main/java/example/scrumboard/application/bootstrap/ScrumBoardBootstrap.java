package example.scrumboard.application.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import example.ddd.domain.ApplicationBootstrapEvent;
import example.scrumboard.application.ScrumBoardFinder;
import example.scrumboard.application.ScrumBoardService;
import example.scrumboard.domain.product.ProductId;

@Component
public class ScrumBoardBootstrap implements ApplicationListener<ApplicationBootstrapEvent> {

	@Autowired
	private ScrumBoardFinder scrumBoardFinder;

	@Autowired
	private ScrumBoardService scrumBoardService;

	@Override
	public void onApplicationEvent(ApplicationBootstrapEvent event) {
		if (scrumBoardFinder.productsCount() == 0) {
			initializeProducts();
		}
	}

	private void initializeProducts() {
		ProductId productId1 = scrumBoardService.createProduct("Example DDD server");
		scrumBoardService.createBacklogItem(productId1, "Write documentation");
		scrumBoardService.createBacklogItem(productId1, "Add more unit tests");

		ProductId productId2 = scrumBoardService.createProduct("Example DDD client");
		// no backlog items
	}

}
