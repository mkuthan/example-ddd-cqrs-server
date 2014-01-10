package example.scrumboard.application.finders;

import java.util.List;

import org.springframework.data.domain.Pageable;

import example.scrumboard.application.finders.dto.ProductBacklogItemDto;
import example.scrumboard.application.finders.dto.ProductDto;

public interface ProductFinder {

	List<ProductDto> findAll(Pageable pageable);

	ProductDto findById(String productId);

	List<ProductBacklogItemDto> findProductBacklogItemsById(String productId);

	long count();

}
