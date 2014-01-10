package example.scrumboard.infrastructure.jpa.repositories;

import example.ddd.infrastructure.GenericJpaRepository;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@JpaRepository
public class JpaProductRepository extends GenericJpaRepository<Product, ProductId> implements ProductRepository {
}
