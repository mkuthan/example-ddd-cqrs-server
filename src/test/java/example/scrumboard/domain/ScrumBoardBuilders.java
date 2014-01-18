package example.scrumboard.domain;

import example.scrumboard.domain.backlogitem.BacklogItemBuilder;
import example.scrumboard.domain.sprint.SprintBuilder;

public class ScrumBoardBuilders {

	public static BacklogItemBuilder givenBacklogItem() {
		return new BacklogItemBuilder();
	}

	public static SprintBuilder givenSprint() {
		return new SprintBuilder();
	}

}
