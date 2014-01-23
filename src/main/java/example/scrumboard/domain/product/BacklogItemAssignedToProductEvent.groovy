package example.scrumboard.domain.product

import static java.util.Objects.requireNonNull
import example.ddd.domain.Event
import example.scrumboard.domain.backlogitem.BacklogItemId
import groovy.transform.Immutable
import groovy.transform.TypeChecked

@Immutable(knownImmutableClasses = [ProductId.class, BacklogItemId.class])
@TypeChecked
class BacklogItemAssignedToProductEvent implements Event {
	ProductId productId
	BacklogItemId backlogItemId
}
