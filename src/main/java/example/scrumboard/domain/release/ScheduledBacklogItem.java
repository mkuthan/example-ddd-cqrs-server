package example.scrumboard.domain.release;

import static java.util.Objects.requireNonNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Predicate;

import example.scrumboard.domain.backlog.item.BacklogItemId;

@Entity
public class ScheduledBacklogItem {

	static Predicate<ScheduledBacklogItem> hasId(final BacklogItemId id) {
		return new Predicate<ScheduledBacklogItem>() {
			@Override
			public boolean apply(ScheduledBacklogItem input) {
				return input.getId().equals(id);
			}
		};
	}

	@Id
	@GeneratedValue
	private Long entityId;

	@Embedded
	private BacklogItemId id;

	ScheduledBacklogItem() {
	}

	ScheduledBacklogItem(BacklogItemId id) {
		this.id = requireNonNull(id);
	}

	BacklogItemId getId() {
		return id;
	}

}
