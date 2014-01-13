package example.scrumboard.domain.sprint;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.backlog.item.BacklogItem;

public class Sprint extends AggregateRoot<SprintId> {

	Sprint(SprintId id) {
		super(id);
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
