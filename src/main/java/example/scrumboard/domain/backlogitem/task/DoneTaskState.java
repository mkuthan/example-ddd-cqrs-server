package example.scrumboard.domain.backlogitem.task;

public class DoneTaskState extends TaskStateAdapter {

	@Override
	public boolean isDone() {
		return true;
	}
}
