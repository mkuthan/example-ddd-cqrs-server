package example.scrumboard.domain.backlogitem.task;

public interface TaskState {

	boolean isInProgress();

	boolean isDone();

	void start(Task task);

	void finish(Task task);

}
