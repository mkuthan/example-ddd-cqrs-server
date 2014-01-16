package example.scrumboard.application.impl;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.task.TaskId;

@ApplicationService
public class TaskServiceImpl {

	public TaskId createTask(BacklogItemId backlogItemId, String name, Integer hoursRemaining) {
		return null;
	}

	public void start(TaskId taskId) {
	}

	public void finish(TaskId taskId) {
	}
	
	public void estimateHoursRemaining(TaskId taskId, Integer hoursRemaing) {
	}

}
