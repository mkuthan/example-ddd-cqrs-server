package example.scrumboard.domain.backlog.item;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.sprint.Sprint;

public class BacklogItem extends AggregateRoot<BacklogItemId> {

	public void commitTo(Sprint sprint) {
		// TODO Auto-generated method stub

	}

	BacklogItem(BacklogItemId id) {
		super(id);
	}

}
