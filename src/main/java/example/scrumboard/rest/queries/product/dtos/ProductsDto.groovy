package example.scrumboard.rest.queries.product.dtos

import groovy.transform.Immutable

@Immutable
class ProductDto {
	String productId
	String productName
	int backlogItemsCount
}
