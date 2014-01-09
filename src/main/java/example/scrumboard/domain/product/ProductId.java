package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.ValueObject;

public class ProductId extends ValueObject {

	private static final long serialVersionUID = 1L;

	private String id;

	ProductId() {
	}

	public ProductId(String id) {
		this.id = requireNonNull(id);
	}

	public String getId() {
		return id;
	}
	
}
