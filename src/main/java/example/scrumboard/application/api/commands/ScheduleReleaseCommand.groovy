package example.scrumboard.application.api.commands

import example.scrumboard.domain.product.ProductId
import groovy.transform.Canonical


@Canonical
class ScheduleReleaseCommand {
	ProductId productId
	String releaseName
	Date releaseDate
}
