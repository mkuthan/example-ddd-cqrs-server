package example.scrumboard.application.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import example.ddd.domain.ApplicationBootstrapEvent;
import example.scrumboard.application.ScrumBoardFinder;
import example.scrumboard.application.ScrumBoardService;

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
		scrumBoardService.createProduct("Example DDD server");
		scrumBoardService.createProduct("Example DDD client");
	}

}
