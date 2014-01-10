package example.scrumboard.domain.backlog.item;

import static java.util.Objects.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

import example.ddd.domain.AggregateRoot;
import example.scrumboard.domain.sprint.Sprint;

@Entity
public class BacklogItem extends AggregateRoot<BacklogItemId> {

	@Column(nullable = false)
	private String name;

	BacklogItem() {
	}

	BacklogItem(BacklogItemId id, String name) {
		super(id);
		this.name = requireNonNull(name);
	}

	public void commitTo(Sprint sprint) {
		// TODO Auto-generated method stub
	}

}
