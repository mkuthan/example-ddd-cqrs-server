package example.ddd.scrumboard.domain.backlog.item;

import example.ddd.scrumboard.domain.shared.AggregateRoot;
import example.ddd.scrumboard.domain.shared.EventPublisher;
import example.ddd.scrumboard.domain.sprint.Sprint;

public class BacklogItem extends AggregateRoot<BacklogItemId> {

	public void commitTo(Sprint sprint) {
		// TODO Auto-generated method stub

	}

	BacklogItem(BacklogItemId id, EventPublisher eventPublisher) {
		super(id, eventPublisher);
	}

}
