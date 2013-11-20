package example.ddd.scrumboard.domain.backlog.task;

import example.ddd.scrumboard.domain.shared.UniqueIdentifier;

public class TaskId extends UniqueIdentifier<String> {

	public TaskId(String id) {
		super(id);
	}

}
