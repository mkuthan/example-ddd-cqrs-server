package example.scrumboard.application;

import java.util.List;

import org.springframework.data.domain.Pageable;

import example.scrumboard.application.dto.ProductBacklogItemDto;
import example.scrumboard.application.dto.ProductDto;

public interface ProductFinder {

	List<ProductDto> findAll(Pageable pageable);

	ProductDto findById(String productId);

	List<ProductBacklogItemDto> findProductBacklogItemsById(String productId);

	long count();

}
