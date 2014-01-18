package example.scrumboard.domain.backlogitem.task;

public class IllegalTaskStateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Task task;

	private String operation;

	public IllegalTaskStateException(Task task, String operation) {
		this.task = task;
		this.operation = operation;
	}

	@Override
	public String getMessage() {
		return "Cannot " + operation + " on " + task + ".";
	}

}
