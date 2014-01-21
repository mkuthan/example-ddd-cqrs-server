package example.scrumboard.application.api.commands

import example.scrumboard.domain.product.ProductId
import groovy.transform.Canonical


@Canonical
class ScheduleSprintCommand {
	ProductId productId
	String sprintName
	Date sprintBeginDate
	Date sprintEndDate
}
