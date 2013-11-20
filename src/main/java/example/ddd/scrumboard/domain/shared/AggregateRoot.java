package example.ddd.scrumboard.domain.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode(of = "id")
@ToString(exclude = "eventPublisher")
public abstract class AggregateRoot<ID> {

	@Getter
	private ID id;

	@Getter
	private Integer version;

	private EventPublisher eventPublisher;

	protected AggregateRoot(@NonNull ID id) {
		this.id = id;
		this.version = 0;
	}

	protected void publish(@NonNull DomainEvent domainEvent) {
		if (eventPublisher == null) {
			throw new IllegalStateException("Could not publish event, event publisher is not initialized");
		}
		eventPublisher.publish(domainEvent);
	}

}
