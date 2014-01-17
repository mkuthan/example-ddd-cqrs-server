package example.scrumboard.domain.sprint;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.product.ProductId;

public class Sprint extends AggregateRoot<SprintId> {

	private ProductId productId;

	Sprint(SprintId id, ProductId productId) {
		super(id);
		this.productId = requireNonNull(productId);
	}

	public void commit(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
	}

	public void uncommit(BacklogItem backlogItem) {
		// TODO Auto-generated method stub

	}

	public void captureRetrospective(String retrospective) {
		// TODO Auto-generated method stub
	}

}
