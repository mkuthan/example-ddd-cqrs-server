package example.ddd.scrumboard.domain.backlog.task;

import static java.util.Objects.requireNonNull;
import example.ddd.scrumboard.domain.shared.AggregateRoot;
import example.ddd.scrumboard.domain.shared.EventPublisher;

public class Task extends AggregateRoot<TaskId> {

	private TaskId id;

	private TaskStatus status;

	Task(TaskId id, TaskStatus status, EventPublisher eventPublisher) {
		super(id, eventPublisher);
		this.status = requireNonNull(status);
	}

	public TaskId getId() {
		return id;
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

	TaskStatus getStatus() {
		return status;
	}

}
