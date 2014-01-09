package example.scrumboard.domain.backlog.task;

public class TodoTaskState extends TaskStateAdapter {
	
	@Override
	public void start(Task task) {
		task.doStart();
	}
	
}
