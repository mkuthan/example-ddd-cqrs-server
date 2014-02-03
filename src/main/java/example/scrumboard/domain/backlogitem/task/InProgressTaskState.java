package example.scrumboard.domain.backlogitem.task;

import java.util.Date;

public class InProgressTaskState extends TaskStateAdapter {

	@Override
	public boolean isInProgress() {
		return true;
	}

	@Override
	public void finish(Task task) {
		task.doFinish();
	}

	@Override
	public void amendHoursRemaining(Task task, Date effectiveDate, Integer hoursRemaing) {
		task.doAmendHoursRemaining(effectiveDate, hoursRemaing);
	}

}
