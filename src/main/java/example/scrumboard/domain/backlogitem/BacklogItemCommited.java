package example.scrumboard.domain.backlogitem;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.Event;
import example.scrumboard.domain.sprint.SprintId;

public class BacklogItemCommited extends Event {

	private static final long serialVersionUID = 1L;

	private BacklogItemId backlogItemId;

	private SprintId sprintId;

	public BacklogItemCommited(BacklogItemId backlogItemId, SprintId sprintId) {
		this.backlogItemId = requireNonNull(backlogItemId);
		this.sprintId = requireNonNull(sprintId);
	}

	public BacklogItemId getBacklogItemId() {
		return backlogItemId;
	}

	public SprintId getSprintId() {
		return sprintId;
	}

}
