package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.ApplicationService;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.backlogitem.BacklogItemRepository;
import example.scrumboard.domain.backlogitem.task.Task;
import example.scrumboard.domain.backlogitem.task.TaskFactory;
import example.scrumboard.domain.backlogitem.task.TaskId;
import example.scrumboard.domain.backlogitem.task.TaskRepository;

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

	public void amendHoursRemaining(TaskId taskId, Integer hoursRemaing) {
	}

}
