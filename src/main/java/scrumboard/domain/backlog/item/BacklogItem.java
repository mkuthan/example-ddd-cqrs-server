package scrumboard.domain.backlog.item;

import scrumboard.domain.sprint.Sprint;
import ddd.domain.AggregateRoot;

public class BacklogItem extends AggregateRoot<BacklogItemId> {

	public void commitTo(Sprint sprint) {
		// TODO Auto-generated method stub

	}

	BacklogItem(BacklogItemId id) {
		super(id);
	}

}
