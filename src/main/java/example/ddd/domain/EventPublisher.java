package example.ddd.domain;


public interface EventPublisher {

	void publish(Event event);

}
