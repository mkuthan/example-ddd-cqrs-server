package example.scrumboard.application.api.commands

import example.scrumboard.domain.backlogitem.BacklogItemId
import example.scrumboard.domain.product.ProductId
import groovy.transform.Canonical


@Canonical
class ReorderBacklogItemsCommand {
	ProductId productId
	BacklogItemId[] backlogItemIds
}
