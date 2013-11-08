package example.ddd.scrumboard.domain.backlog.task;

import example.ddd.scrumboard.domain.shared.AggregateEntity;

public class Task extends AggregateEntity {

	private TaskId id;

	private TaskStatus status;

	Task(TaskId id, TaskStatus status) {
		this.id = id;
		this.status = status;
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
