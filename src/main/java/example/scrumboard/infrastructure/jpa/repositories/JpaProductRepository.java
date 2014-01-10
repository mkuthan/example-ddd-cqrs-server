package example.scrumboard.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import example.ddd.infrastructure.GenericJpaRepository;
import example.scrumboard.domain.product.Product;
import example.scrumboard.domain.product.ProductId;
import example.scrumboard.domain.product.ProductRepository;

@Component
public class JpaProductRepository extends GenericJpaRepository<Product, ProductId> implements ProductRepository {
}
