package example.ddd.scrumboard.domain.backlog.task;

public class DoneTaskState extends TaskStateAdapter {

	@Override
	public boolean isDone() {
		return true;
	}
}
