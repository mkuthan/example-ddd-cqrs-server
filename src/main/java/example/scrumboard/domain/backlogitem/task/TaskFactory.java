package example.scrumboard.domain.backlogitem.task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import example.scrumboard.domain.backlogitem.BacklogItem;

@Component
public class TaskFactory {

	public Task create(BacklogItem backlogItem, String name, Integer hoursRemaining) {
		TaskId id = new TaskId(UUID.randomUUID().toString());
		TaskStatus status = TaskStatus.TODO;
		List<RemainingAmendment> remainingAmendments = new ArrayList<>();

		return new Task(id, backlogItem, status, name, hoursRemaining, remainingAmendments);
	}
}
