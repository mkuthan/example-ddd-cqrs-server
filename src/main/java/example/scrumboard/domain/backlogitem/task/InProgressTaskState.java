package example.scrumboard.domain.backlogitem.task;

public class InProgressTaskState extends TaskStateAdapter {

	@Override
	public boolean isInProgress() {
		return true;
	}

	@Override
	public void finish(Task task) {
		task.doFinish();
	}

}
