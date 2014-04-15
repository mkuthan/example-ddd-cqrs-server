package example.scrumboard.domain.backlogitem;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static example.scrumboard.domain.ScrumBoardBuilders.givenSprint;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import example.scrumboard.TestGroups;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;

@Test(groups = TestGroups.UNIT)
public class BacklogItemTest {

	private BacklogItem backlogItem;

	private BacklogItemBuilder backlogItemBuilder;

	public void shouldCommitToSprint() {
		SprintId sprintId = new SprintId("id");
		Sprint sprint = givenSprint().withId(sprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId);

		whenBacklogItem().commitToSprint(sprint);

		// @formatter:off
		thenBacklogItem()
			.isCommitedToSprint(sprintId)
			.backlogItemCommitedPublished(sprintId);
		// @formatter:on
	}

	public void shouldNotCommitToSprintWhenNoChanges() {
		SprintId sprintId = new SprintId("id");
		Sprint sprint = givenSprint().withId(sprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId).commitedToSprint(sprint);

		whenBacklogItem().commitToSprint(sprint);

		// @formatter:off
		thenBacklogItem()
			.isCommitedToSprint(sprint.getId())
			.eventNotPublished();
		// @formatter:on
	}

	public void shouldCommitToSprintWhenCommited() {
		SprintId oldSprintId = new SprintId("old");
		Sprint oldSprint = givenSprint().withId(oldSprintId).build();

		SprintId newSprintId = new SprintId("new");
		Sprint newSprint = givenSprint().withId(newSprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId).commitedToSprint(oldSprint);

		whenBacklogItem().commitToSprint(newSprint);

		// @formatter:off
		thenBacklogItem()
			.isCommitedToSprint(newSprintId)
			.backlogItemUncommitedPublished(oldSprintId)
			.backlogItemCommitedPublished(newSprintId);
		// @formatter:on
	}

	public void shouldUncommitFromSprint() {
		SprintId sprintId = new SprintId("id");
		Sprint sprint = givenSprint().withId(sprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId).commitedToSprint(sprint);

		whenBacklogItem().uncommitFromSprint(sprint);

		// @formatter:off
		thenBacklogItem()
			.isNotCommited()
			.backlogItemUncommitedPublished(sprintId);
		// @formatter:on
	}

	public void shouldNotUncommitFromSprintWhenNotCommited() {
		SprintId sprintId = new SprintId("id");
		Sprint sprint = givenSprint().withId(sprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId);

		catchException(whenBacklogItem()).uncommitFromSprint(sprint);

		assertThat(caughtException()).isInstanceOf(IllegalArgumentException.class);
		thenBacklogItem().eventNotPublished();
	}

	public void shouldNotUncommitFromSprintWhenNotCommitedToGivenSprint() {
		SprintId oldSprintId = new SprintId("old");
		Sprint oldSprint = givenSprint().withId(oldSprintId).build();

		SprintId newSprintId = new SprintId("new");
		Sprint newSprint = givenSprint().withId(newSprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId).commitedToSprint(oldSprint);

		catchException(whenBacklogItem()).uncommitFromSprint(newSprint);

		assertThat(caughtException()).isInstanceOf(IllegalArgumentException.class);
		thenBacklogItem().eventNotPublished();
	}

	private BacklogItemBuilder givenBacklogItem() {
		backlogItemBuilder = new BacklogItemBuilder();
		return backlogItemBuilder;
	}

	private BacklogItem whenBacklogItem() {
		backlogItem = backlogItemBuilder.build();
		return backlogItem;
	}

	private BacklogItemAssert thenBacklogItem() {
		return new BacklogItemAssert(backlogItem);
	}

}
