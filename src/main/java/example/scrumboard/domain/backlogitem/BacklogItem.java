package example.scrumboard.domain.backlogitem;

import static java.util.Objects.requireNonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.release.Release;
import example.scrumboard.domain.sprint.Sprint;
import example.scrumboard.domain.sprint.SprintId;

@Entity
public class BacklogItem extends AggregateRoot<BacklogItemId> {

	@Column(nullable = false)
	private String story;

	@Embedded
	@AttributeOverride(name = "id", column = @Column(nullable = true))
	private SprintId sprintId;

	BacklogItem() {
	}

	BacklogItem(BacklogItemId id, String story) {
		super(id);
		this.story = requireNonNull(story);
	}

	BacklogItem(BacklogItemId id, String story, Sprint sprint) {
		this(id, story);
		if (sprint != null) {
			this.sprintId = sprint.getId();
		}
	}

	public void commitToSprint(Sprint sprint) {
		requireNonNull(sprint);

		if (isCommited()) {
			if (sprintId.equals(sprint.getId())) {
				return;
			} else {
				uncommitFromSprint();
			}
		}

		this.sprintId = sprint.getId();
		publish(new BacklogItemCommited(getId(), sprint.getId()));
	}

	public void uncommitFromSprint(Sprint sprint) {
		requireNonNull(sprint);

		if (!isCommited()) {
			throw new IllegalArgumentException("Backlog item " + getId() + " is not commited.");
		}

		if (!sprintId.equals(sprint.getId())) {
			throw new IllegalArgumentException("Backlog item " + getId() + " is commited to " + sprintId
					+ " but expected " + sprint.getId() + ".");
		}

		uncommitFromSprint();
	}

	public void assignStoryPoints(StoryPoints storyPoints) {
		// TODO Auto-generated method stub
	}

	public void assignPriority(BacklogItemPriority priority) {
		// TODO Auto-generated method stub
	}

	public void scheduleToRelease(Release release) {
		// TODO Auto-generated method stub

	}

	public void unscheduleFromRelease(Release release) {
		// TODO Auto-generated method stub
	}

	SprintId getSprintId() {
		return sprintId;
	}

	private boolean isCommited() {
		return sprintId != null;
	}

	private void uncommitFromSprint() {
		SprintId sprintId = this.sprintId;
		this.sprintId = null;
		publish(new BacklogItemUncommited(getId(), sprintId));
	}

}
