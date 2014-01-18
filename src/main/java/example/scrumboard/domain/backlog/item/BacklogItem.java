package example.scrumboard.domain.backlog.item;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.release.Release;
import example.scrumboard.domain.sprint.Sprint;

@Entity
public class BacklogItem extends AggregateRoot<BacklogItemId> {

	@Column(nullable = false)
	private String story;

	BacklogItem() {
	}

	BacklogItem(BacklogItemId id, String story) {
		super(id);
		this.story = requireNonNull(story);
	}

	public void commitToSprint(Sprint sprint) {
		// TODO Auto-generated method stub
	}

	public void uncommitFromSprint(Sprint sprint) {
		// TODO Auto-generated method stub
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

}
