package example.scrumboard.application.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import example.ddd.domain.ApplicationBootstrapEvent;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@Component
public class ScrumBoardBootstrap implements ApplicationListener<ApplicationBootstrapEvent> {

	@Autowired
	private ProductService productServiceImpl;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void onApplicationEvent(ApplicationBootstrapEvent event) {
		if (productRepository.count() == 0) {
			initializeProducts();
		}
	}

	private void initializeProducts() {
		ProductId productId1 = productServiceImpl.createProduct("Example DDD/CQRS server");
		productServiceImpl.createProductBacklogItem(productId1, "Write documentation");
		productServiceImpl.createProductBacklogItem(productId1, "Add more unit tests");

		ProductId productId2 = productServiceImpl.createProduct("Example DDD/CQRS client");
		productServiceImpl.createProductBacklogItem(productId2, "Apply Twitter Bootstrap");
		productServiceImpl.createProductBacklogItem(productId2, "Create Angular controllers");

		productServiceImpl.createProduct("Product with no backlog items");
	}

}
