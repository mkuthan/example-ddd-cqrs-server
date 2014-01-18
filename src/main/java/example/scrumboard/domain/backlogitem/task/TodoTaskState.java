package example.scrumboard.domain.backlogitem.task;

public class TodoTaskState extends TaskStateAdapter {
	
	@Override
	public void start(Task task) {
		task.doStart();
	}
	
}
