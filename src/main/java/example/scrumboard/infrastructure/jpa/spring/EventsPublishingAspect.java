package example.scrumboard.infrastructure.jpa.spring;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.ddd.AggregateRoot;
import example.ddd.Event;
import example.ddd.EventPublisher;

@Component
@Aspect
public class EventsPublishingAspect {

	@Autowired
	private EventPublisher eventPublisher;

	@AfterReturning(pointcut = "execution(public * example.ddd.Repository+.save(..)) && args(aggregateRoot)")
	public void publishPendingEvents(AggregateRoot<?> aggregateRoot) {
		for (Event event : aggregateRoot.getPendingEvents()) {
			eventPublisher.publish(event);
		}
	}

}
