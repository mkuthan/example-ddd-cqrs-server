package example.scrumboard.domain.backlogitem.task;

import java.util.Date;

public interface TaskState {

	boolean isTodo();

	boolean isInProgress();

	boolean isDone();

	void begin(Task task);

	void finish(Task task);

	void amendHoursRemaining(Task task, Date effectiveDate, Integer hoursRemaing);

}
