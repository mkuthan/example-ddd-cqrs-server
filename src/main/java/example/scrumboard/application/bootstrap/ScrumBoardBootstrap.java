package example.scrumboard.application.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import example.ddd.domain.ApplicationBootstrapEvent;
import example.scrumboard.application.ProductFinder;
import example.scrumboard.application.ProductService;
import example.scrumboard.domain.product.ProductId;

@Component
public class ScrumBoardBootstrap implements ApplicationListener<ApplicationBootstrapEvent> {

	@Autowired
	private ProductFinder productFinder;

	@Autowired
	private ProductService productService;

	@Override
	public void onApplicationEvent(ApplicationBootstrapEvent event) {
		if (productFinder.count() == 0) {
			initializeProducts();
		}
	}

	private void initializeProducts() {
		ProductId productId1 = productService.createProduct("Example DDD server");
		productService.createBacklogItem(productId1, "Write documentation");
		productService.createBacklogItem(productId1, "Add more unit tests");

		ProductId productId2 = productService.createProduct("Example DDD client");
		// no backlog items
	}

}
