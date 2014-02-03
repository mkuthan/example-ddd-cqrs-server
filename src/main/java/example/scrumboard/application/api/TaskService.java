package example.scrumboard.application.api;

import example.scrumboard.application.api.commands.CreateTaskCommand;
import example.scrumboard.domain.backlogitem.task.TaskId;

public interface TaskService {

	TaskId createTask(CreateTaskCommand command);

	void beginTask(TaskId taskId);

	void finishTask(TaskId taskId);

	void amendHoursRemaining(TaskId taskId, Integer hoursRemaing);

}