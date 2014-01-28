package example.scrumboard.domain.product;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.ddd.ValueObject;

@Embeddable
public class ProductId extends ValueObject {

	private static final long serialVersionUID = 1L;

	@Column(name = "product_id", nullable = false)
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
