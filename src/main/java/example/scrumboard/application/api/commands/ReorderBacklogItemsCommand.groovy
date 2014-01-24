package example.scrumboard.application.api.commands

import example.scrumboard.domain.backlogitem.BacklogItemId
import groovy.transform.Canonical


@Canonical
class ReorderBacklogItemsCommand {
	BacklogItemId[] backlogItemIds
}
