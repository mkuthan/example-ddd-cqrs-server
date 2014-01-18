package example.scrumboard.domain.backlogitem;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import example.scrumboard.domain.sprint.SprintId;

public class BacklogItemAssert extends AbstractAssert<BacklogItemAssert, BacklogItem> {

	public BacklogItemAssert(BacklogItem actual) {
		super(actual, BacklogItemAssert.class);
	}

	public BacklogItemAssert isCommitedToSprint(SprintId sprintId) {
		assertThat(actual.getSprintId()).isEqualTo(sprintId);
		return this;
	}

}
