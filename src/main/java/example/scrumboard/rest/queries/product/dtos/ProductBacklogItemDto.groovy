package example.scrumboard.rest.queries.product.dtos

import groovy.transform.Immutable

@Immutable
class ProductBacklogItemDto {
	String backlogItemId
	String backlogItemStory
	int backlogItemPosition
}
