package example.scrumboard.infrastructure.rest;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import example.scrumboard.domain.product.ProductId;

@Component
public class ProductIdConverter implements Converter<String, ProductId> {

	@Override
	public ProductId convert(String source) {
		return new ProductId(source);
	}

}
