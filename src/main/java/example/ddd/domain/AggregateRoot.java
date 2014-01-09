package example.ddd.domain;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public abstract class AggregateRoot<ID> {

	private ID id;

	private Integer version;

	private EventPublisher eventPublisher;

	protected AggregateRoot() {
	}

	protected AggregateRoot(ID id) {
		this.id = requireNonNull(id);
		this.version = 0;
	}

	public ID getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		AggregateRoot<ID> that = (AggregateRoot<ID>) obj;

		return Objects.equals(this.id, that.id);
	}

	protected void publish(Event event) {
		requireNonNull(event);

		if (eventPublisher == null) {
			throw new IllegalStateException("Could not publish event, event publisher is not initialized");
		}

		eventPublisher.publish(event);
	}

}
