package example.scrumboard.domain.product;

import example.ddd.domain.Repository;

public interface ProductRepository extends Repository<Product, ProductId> {
	Product load(ProductId productId);
}
