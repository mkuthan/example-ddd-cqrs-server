package example.ddd.scrumboard.domain.sprint;

import example.ddd.scrumboard.domain.backlog.item.ProductBacklogItem;
import example.ddd.scrumboard.domain.shared.AggregateEntity;

public class Sprint extends AggregateEntity {

	private SprintId id;

	Sprint(SprintId id) {
		this.id = id;
	}

	public SprintId getId() {
		return id;
	}

	public void commitProductBacklogItem(ProductBacklogItem productBacklogItem) {
	}

	static class Builder {

		private SprintId id;

		public Builder withId(SprintId id) {
			this.id = id;
			return this;
		}

		public Sprint build() {
			return new Sprint(id);
		}
	}

}
