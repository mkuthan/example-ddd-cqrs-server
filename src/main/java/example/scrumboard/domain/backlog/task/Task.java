package example.scrumboard.domain.backlog.task;

import static java.util.Objects.requireNonNull;
import example.ddd.domain.AggregateRoot;

public class Task extends AggregateRoot<TaskId> {

	private TaskStatus status;

	Task() {
	}

	Task(TaskId id, TaskStatus status) {
		super(id);
		this.status = requireNonNull(status);
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
