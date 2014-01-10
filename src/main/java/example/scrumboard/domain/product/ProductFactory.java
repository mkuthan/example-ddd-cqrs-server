package example.scrumboard.domain.product;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

	public Product create(String name) {
		ProductId id = new ProductId(UUID.randomUUID().toString());
		Set<ProductBacklogItem> backlogItems = new HashSet<>();

		return new Product(id, name, backlogItems);
	}

}
