package example.ddd.scrumboard.domain.product;

import example.ddd.scrumboard.domain.shared.Repository;

public interface ProductRepository extends Repository<Product, ProductId> {
	Product load(ProductId productId);
}
