package example.ddd.scrumboard.domain.backlog.task;

public class TaskStateAdapter implements TaskState {

	@Override
	public boolean isInProgress() {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public void start(Task task) {
		throw new IllegalTaskStateException(task, "start");
	}

	@Override
	public void finish(Task task) {
		throw new IllegalTaskStateException(task, "finish");
	}

}
