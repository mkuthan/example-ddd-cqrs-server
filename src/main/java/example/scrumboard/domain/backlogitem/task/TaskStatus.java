package example.scrumboard.domain.backlogitem.task;

import java.util.Date;

public enum TaskStatus implements TaskState {

	// @formatter:off
	TODO(new TodoTaskState()),
	IN_PROGRESS(new InProgressTaskState()), 
	DONE(new DoneTaskState());
	// @formatter:on

	private TaskState state;

	private TaskStatus(TaskState state) {
		this.state = state;
	}

	@Override
	public boolean isTodo() {
		return state.isTodo();
	}

	@Override
	public boolean isInProgress() {
		return state.isInProgress();
	}

	@Override
	public boolean isDone() {
		return state.isDone();
	}

	@Override
	public void begin(Task task) {
		state.begin(task);
	}

	@Override
	public void finish(Task task) {
		state.finish(task);
	}

	@Override
	public void amendHoursRemaining(Task task, Date effectiveDate, Integer hoursRemaing) {
		state.amendHoursRemaining(task, effectiveDate, hoursRemaing);
	}

}
