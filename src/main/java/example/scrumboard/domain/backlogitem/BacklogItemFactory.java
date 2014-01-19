package example.scrumboard.domain.backlogitem;

import java.util.UUID;

import org.springframework.stereotype.Component;

import example.scrumboard.domain.product.Product;

@Component
public class BacklogItemFactory {

	public BacklogItem create(Product product, String story) {
		BacklogItemId id = new BacklogItemId(UUID.randomUUID().toString());
		return new BacklogItem(id, product, story);
	}
}
