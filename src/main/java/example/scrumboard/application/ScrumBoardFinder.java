package example.scrumboard.application;

import example.scrumboard.application.dto.ProductDto;

public interface ScrumBoardFinder {

	long productsCount();

	ProductDto findProductByName(String name);
	
}
