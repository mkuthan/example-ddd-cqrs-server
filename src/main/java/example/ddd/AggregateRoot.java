package example.ddd;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Autowired;

@MappedSuperclass
public abstract class AggregateRoot<ID> {

	@Id
	@Embedded
	private ID id;

	@Version
	private Integer version;

	@Transient
	@Autowired
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
