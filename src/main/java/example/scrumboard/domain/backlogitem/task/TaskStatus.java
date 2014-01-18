package example.scrumboard.domain.backlogitem.task;

public enum TaskStatus implements TaskState {
	TODO, IN_PROGRESS, DONE;

	@Override
	public boolean isInProgress() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finish(Task task) {
		// TODO Auto-generated method stub

	}

}
