package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.domain.ApplicationService;
import example.scrumboard.domain.backlog.item.BacklogItem;
import example.scrumboard.domain.backlog.item.BacklogItemId;
import example.scrumboard.domain.backlog.item.BacklogItemRepository;
import example.scrumboard.domain.backlog.task.Task;
import example.scrumboard.domain.backlog.task.TaskFactory;
import example.scrumboard.domain.backlog.task.TaskId;
import example.scrumboard.domain.backlog.task.TaskRepository;

@ApplicationService
public class TaskServiceImpl {

	@Autowired
	private TaskFactory taskFactory;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	public TaskId createTask(BacklogItemId backlogItemId, String name, Integer hoursRemaining) {
		BacklogItem backlogItem = backlogItemRepository.load(backlogItemId);

		Task task = taskFactory.create(backlogItem, name, hoursRemaining);
		taskRepository.save(task);

		return task.getId();
	}

	public void start(TaskId taskId) {
	}

	public void finish(TaskId taskId) {
	}

	public void amendeHoursRemaining(TaskId taskId, Integer hoursRemaing) {
	}

}
