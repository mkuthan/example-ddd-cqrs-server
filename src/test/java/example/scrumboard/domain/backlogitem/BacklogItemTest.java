package example.scrumboard.domain.backlogitem;

import static example.ddd.domain.DddAssertions.thenEvent;
import static example.scrumboard.domain.ScrumBoardBuilders.givenSprint;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import example.ddd.domain.EventPublisher;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;

@Test
public class BacklogItemTest {

	@Mock
	private EventPublisher eventPublisher;

	@InjectMocks
	private BacklogItem backlogItem;

	private BacklogItemBuilder backlogItemBuilder;

	public void shouldCommitToSprint() {
		SprintId sprintId = new SprintId("id");
		Sprint sprint = givenSprint().withId(sprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId);

		whenBacklogItem().commitToSprint(sprint);

		thenBacklogItem().isCommitedToSprint(sprintId);
		thenEvent(eventPublisher).published(new BacklogItemCommited(backlogItemId, sprintId));
	}

	public void shouldNotCommitToSprintWhenNoChanges() {
		SprintId sprintId = new SprintId("id");
		Sprint sprint = givenSprint().withId(sprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId).commitedToSprint(sprint);

		whenBacklogItem().commitToSprint(sprint);

		thenBacklogItem().isCommitedToSprint(sprint.getId());
		thenEvent(eventPublisher).notPublished();
	}

	public void shouldCommitToSprintWhenCommited() {
		SprintId oldSprintId = new SprintId("old");
		Sprint oldSprint = givenSprint().withId(oldSprintId).build();

		SprintId newSprintId = new SprintId("new");
		Sprint newSprint = givenSprint().withId(newSprintId).build();

		BacklogItemId backlogItemId = new BacklogItemId("id");
		givenBacklogItem().withId(backlogItemId).commitedToSprint(oldSprint);

		whenBacklogItem().commitToSprint(newSprint);

		thenBacklogItem().isCommitedToSprint(newSprintId);

		// @formatter:off
		thenEvent(eventPublisher)
			.published(new BacklogItemUncommited(backlogItemId, oldSprintId))
			.published(new BacklogItemCommited(backlogItemId, newSprintId));
		// @formatter:on
	}

	private BacklogItemBuilder givenBacklogItem() {
		backlogItemBuilder = new BacklogItemBuilder();
		return backlogItemBuilder;
	}

	private BacklogItem whenBacklogItem() {
		backlogItem = backlogItemBuilder.build();

		MockitoAnnotations.initMocks(this);

		return backlogItem;
	}

	private BacklogItemAssert thenBacklogItem() {
		return new BacklogItemAssert(backlogItem);
	}

}
