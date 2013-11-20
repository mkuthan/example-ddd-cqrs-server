package example.ddd.scrumboard.domain.sprint;

import example.ddd.scrumboard.domain.shared.EventPublisher;

class SprintBuilder {

	private SprintId id;

	private EventPublisher eventPublisher;

	public SprintBuilder sprint(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		return this;
	}

	public SprintBuilder withId(SprintId id) {
		this.id = id;
		return this;
	}

	public Sprint build() {
		return new Sprint(id, eventPublisher);
	}
}