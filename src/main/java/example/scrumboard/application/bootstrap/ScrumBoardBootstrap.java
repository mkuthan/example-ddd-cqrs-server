package example.scrumboard.application.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import example.bootstrap.domain.BootstrapEvent;
import example.bootstrap.domain.BootstrapListener;
import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@BootstrapListener
public class ScrumBoardBootstrap implements ApplicationListener<BootstrapEvent> {

	@Autowired
	private ProductService productServiceImpl;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (productRepository.count() == 0) {
			initializeProducts();
		}
	}

	private void initializeProducts() {
		ProductId productId1 = productServiceImpl.createProduct("Example DDD/CQRS server");
		productServiceImpl.planBacklogItem(productId1, "Write documentation");
		productServiceImpl.planBacklogItem(productId1, "Add more unit tests");

		ProductId productId2 = productServiceImpl.createProduct("Example DDD/CQRS client");
		productServiceImpl.planBacklogItem(productId2, "Apply Twitter Bootstrap");
		productServiceImpl.planBacklogItem(productId2, "Create Angular controllers");

		productServiceImpl.createProduct("Product with no backlog items");
	}

}
