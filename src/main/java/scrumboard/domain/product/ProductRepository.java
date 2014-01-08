package scrumboard.domain.product;

import ddd.domain.Repository;

public interface ProductRepository extends Repository<Product, ProductId> {
	Product load(ProductId productId);
}
