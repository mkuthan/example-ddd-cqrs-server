package example.scrumboard.rest.commands.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import example.scrumboard.application.api.ProductService;
import example.scrumboard.domain.product.ProductId;

@RestController
public class ProductCommandController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProductId create(String name) {
		return productService.createProduct(name);
	}
}
