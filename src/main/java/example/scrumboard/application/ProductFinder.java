package example.scrumboard.application;

import java.util.List;

import example.scrumboard.application.dto.ProductBacklogItemDto;
import example.scrumboard.application.dto.ProductDto;

public interface ProductFinder {

	List<ProductDto> findAll();

	ProductDto findById(String productId);

	List<ProductBacklogItemDto> findProductBacklogItemsById(String productId);

	long count();

}
