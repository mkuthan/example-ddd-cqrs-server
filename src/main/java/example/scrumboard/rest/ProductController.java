package example.scrumboard.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.scrumboard.application.finders.ProductFinder;
import example.scrumboard.application.finders.dto.ProductBacklogItemDto;
import example.scrumboard.application.finders.dto.ProductDto;

@RestController
public class ProductController {

	@Autowired
	private ProductFinder productFinder;

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductDto> product(Pageable pageable) {
		return new PageImpl<>(productFinder.findAll(pageable), pageable, productFinder.count());
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto product(@PathVariable("id") String productId) {
		return productFinder.findById(productId);
	}

	@RequestMapping(value = "/product/{id}/backlogItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductBacklogItemDto> backlogItems(@PathVariable("id") String productId) {
		return productFinder.findProductBacklogItemsById(productId);
	}

}
