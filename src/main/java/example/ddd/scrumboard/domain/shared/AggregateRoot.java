package example.ddd.scrumboard.domain.shared;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public abstract class AggregateRoot<ID extends UniqueIdentifier<?>> {

	private ID id;

	private EventPublisher eventPublisher;

	public ID getId() {
		return id;
	}

	public EventPublisher getEventPublisher() {
		return eventPublisher;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		AggregateRoot<ID> that = (AggregateRoot<ID>) obj;

		return Objects.equals(this.getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}

	protected AggregateRoot(ID id, EventPublisher eventPublisher) {
		this.id = requireNonNull(id);
		this.eventPublisher = requireNonNull(eventPublisher);
	}

}
