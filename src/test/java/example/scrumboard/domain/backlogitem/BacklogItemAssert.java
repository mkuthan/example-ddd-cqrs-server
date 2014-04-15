package example.scrumboard.domain.backlogitem;

import static example.ddd.domain.DddAssertions.assertThat;
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

	public BacklogItemAssert isNotCommited() {
		assertThat(actual.getSprintId()).isNull();
		return this;
	}

	public BacklogItemAssert backlogItemCommitedPublished(SprintId sprintId) {
		assertThat(actual).published(new BacklogItemCommited(actual.getId(), sprintId));
		return this;
	}

	public BacklogItemAssert backlogItemUncommitedPublished(SprintId sprintId) {
		assertThat(actual).published(new BacklogItemUncommited(actual.getId(), sprintId));
		return this;
	}

	public BacklogItemAssert eventNotPublished() {
		assertThat(actual).notPublished();
		return this;
	}
}
