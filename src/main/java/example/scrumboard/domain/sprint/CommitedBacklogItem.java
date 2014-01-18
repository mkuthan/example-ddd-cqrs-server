package example.scrumboard.domain.sprint;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Predicate;

import example.scrumboard.domain.backlogitem.BacklogItemId;

@Entity
public class CommitedBacklogItem {

	static Predicate<CommitedBacklogItem> hasId(final BacklogItemId id) {
		return new Predicate<CommitedBacklogItem>() {
			@Override
			public boolean apply(CommitedBacklogItem input) {
				return input.getId().equals(id);
			}
		};
	}

	@Id
	@GeneratedValue
	private Long entityId;

	@Embedded
	private BacklogItemId id;

	CommitedBacklogItem() {
	}

	CommitedBacklogItem(BacklogItemId id) {
		this.id = requireNonNull(id);
	}

	BacklogItemId getId() {
		return id;
	}

}
