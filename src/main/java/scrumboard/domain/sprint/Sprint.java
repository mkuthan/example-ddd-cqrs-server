package scrumboard.domain.sprint;

import scrumboard.domain.backlog.item.BacklogItem;
import ddd.domain.AggregateRoot;

public class Sprint extends AggregateRoot<SprintId> {

	Sprint(SprintId id) {
		super(id);
	}

	public void commit(BacklogItem backlogItem) {
		// TODO Auto-generated method stub
	}

}
