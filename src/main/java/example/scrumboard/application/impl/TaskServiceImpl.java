package example.scrumboard.application.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.ddd.ApplicationService;
import example.scrumboard.application.api.TaskService;
import example.scrumboard.application.api.commands.CreateTaskCommand;
import example.scrumboard.application.system.DateProvider;
import example.scrumboard.domain.backlogitem.BacklogItem;
import example.scrumboard.domain.backlogitem.BacklogItemRepository;
import example.scrumboard.domain.backlogitem.task.Task;
import example.scrumboard.domain.backlogitem.task.TaskFactory;
import example.scrumboard.domain.backlogitem.task.TaskId;
import example.scrumboard.domain.backlogitem.task.TaskRepository;

@ApplicationService
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskFactory taskFactory;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private BacklogItemRepository backlogItemRepository;

	@Autowired
	private DateProvider dateProvider;

	@Override
	public TaskId createTask(CreateTaskCommand command) {
		BacklogItem backlogItem = backlogItemRepository.load(command.getBacklogItemId());

		Task task = taskFactory.create(backlogItem, command.getTaskName(), command.getTaskDescription(),
				command.getHoursRemaining());
		taskRepository.save(task);

		return task.getId();
	}

	@Override
	public void beginTask(TaskId taskId) {
		Task task = taskRepository.load(taskId);
		task.begin();

		taskRepository.save(task);
	}

	@Override
	public void finishTask(TaskId taskId) {
		Task task = taskRepository.load(taskId);
		task.finish();

		taskRepository.save(task);
	}

	@Override
	public void amendHoursRemaining(TaskId taskId, Integer hoursRemaing) {
		Task task = taskRepository.load(taskId);
		task.amendHoursRemaining(dateProvider.currentDate(), hoursRemaing);

		taskRepository.save(task);
	}

}
