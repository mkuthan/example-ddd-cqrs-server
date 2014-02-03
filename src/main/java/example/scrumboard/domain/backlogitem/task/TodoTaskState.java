package example.scrumboard.domain.backlogitem.task;

public class TodoTaskState extends TaskStateAdapter {

	@Override
	public boolean isTodo() {
		return true;
	}

	@Override
	public void begin(Task task) {
		task.doBegin();
	}

}
