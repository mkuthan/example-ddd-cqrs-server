package example.scrumboard.domain.backlogitem.task;

import java.util.Date;

public class TaskStateAdapter implements TaskState {

	@Override
	public boolean isTodo() {
		return false;
	}

	@Override
	public boolean isInProgress() {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public void begin(Task task) {
		throw new IllegalTaskStateException(task, "start");
	}

	@Override
	public void finish(Task task) {
		throw new IllegalTaskStateException(task, "finish");
	}

	@Override
	public void amendHoursRemaining(Task task, Date effectiveDate, Integer hoursRemaing) {
		throw new IllegalTaskStateException(task, "amend hours remaining");
	}

}
