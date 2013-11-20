package example.ddd.scrumboard.domain.sprint;

import example.ddd.scrumboard.domain.backlog.item.BacklogItem;
import example.ddd.scrumboard.domain.shared.AggregateRoot;
import example.ddd.scrumboard.domain.shared.EventPublisher;

public class Sprint extends AggregateRoot<SprintId> {

	public void commit(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
	}

	Sprint(SprintId id, EventPublisher eventPublisher) {
		super(id, eventPublisher);
	}

}
