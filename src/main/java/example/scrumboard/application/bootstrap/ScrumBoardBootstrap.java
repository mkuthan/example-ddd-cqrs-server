package example.scrumboard.application.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import example.ddd.domain.ApplicationBootstrapEvent;
import example.scrumboard.application.services.BacklogItemService;
import example.scrumboard.application.services.ProductService;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@Component
public class ScrumBoardBootstrap implements ApplicationListener<ApplicationBootstrapEvent> {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BacklogItemService backlogItemService;

	@Override
	public void onApplicationEvent(ApplicationBootstrapEvent event) {
		if (productRepository.count() == 0) {
			initializeProducts();
		}
	}

	private void initializeProducts() {
		ProductId productId1 = productService.createProduct("Example DDD server");
		backlogItemService.createBacklogItem(productId1, "Write documentation");
		backlogItemService.createBacklogItem(productId1, "Add more unit tests");

		ProductId productId2 = productService.createProduct("Example DDD client");
		// no backlog items
	}

}
