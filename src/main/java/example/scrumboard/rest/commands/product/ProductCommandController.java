package example.scrumboard.rest.commands.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import example.scrumboard.application.api.ProductService;
import example.scrumboard.application.api.commands.CreateProductCommand;
import example.scrumboard.application.api.commands.PlanBacklogItemCommand;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.product.ProductId;

@RestController
public class ProductCommandController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ProductId create(@RequestBody CreateProductCommand command) {
		return productService.createProduct(command);
	}

	@RequestMapping(value = "/products/{productId}/backlogItems", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public BacklogItemId planBacklogItem(@PathVariable("productId") ProductId productId,
			@RequestBody PlanBacklogItemCommand command) {
		return productService.planBacklogItem(productId, command);
	}
}
