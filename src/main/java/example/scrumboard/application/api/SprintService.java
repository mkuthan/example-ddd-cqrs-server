package example.scrumboard.application.api;

import example.scrumboard.domain.backlogitem.BacklogItemId;
import example.scrumboard.domain.sprint.SprintId;

public interface SprintService {

	void commitBacklogItem(SprintId sprintId, BacklogItemId backlogItemId);

	void uncommitBacklogItem(SprintId sprintId, BacklogItemId backlogItemId);

	void beginSprint(SprintId sprintId);

	void finishSprint(SprintId sprintId);

	void captureRetrospective(SprintId sprintId, String retrospective);
}