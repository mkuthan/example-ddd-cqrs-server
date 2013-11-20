package example.ddd.scrumboard.domain.product;

import example.ddd.scrumboard.domain.shared.UniqueIdentifier;

public class ProductId extends UniqueIdentifier<String> {

	public ProductId(String id) {
		super(id);
	}

}
