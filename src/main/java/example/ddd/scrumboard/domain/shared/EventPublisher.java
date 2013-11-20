package example.ddd.scrumboard.domain.shared;

public interface EventPublisher {

	void publish(DomainEvent domainEvent);

}
