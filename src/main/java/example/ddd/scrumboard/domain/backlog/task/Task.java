package example.ddd.scrumboard.domain.backlog.task;

import lombok.NonNull;
import example.ddd.scrumboard.domain.shared.AggregateRoot;

public class Task extends AggregateRoot<TaskId> {

	private TaskStatus status;

	Task(@NonNull TaskId id, @NonNull TaskStatus status) {
		super(id);
		this.status = status;
	}

	public void start() {
		status.start(this);
	}

	public void finish() {
		status.finish(this);
	}

	void doStart() {
		status = TaskStatus.IN_PROGRESS;
	}

	void doFinish() {
		status = TaskStatus.DONE;
	}

}
