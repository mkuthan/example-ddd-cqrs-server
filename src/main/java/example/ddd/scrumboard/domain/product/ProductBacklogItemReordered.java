package example.ddd.scrumboard.domain.product;

import java.util.List;

import lombok.NonNull;
import lombok.Value;
import example.ddd.scrumboard.domain.backlog.item.BacklogItemId;
import example.ddd.scrumboard.domain.shared.DomainEvent;

@Value
public class ProductBacklogItemReordered implements DomainEvent {

	@NonNull
	ProductId productId;

	@NonNull
	List<BacklogItemId> backlogItemIds;

}
