package example.ddd.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class ApplicationBootstrapEvent extends ApplicationContextEvent {

	private static final long serialVersionUID = 1L;

	public ApplicationBootstrapEvent(ApplicationContext source) {
		super(source);
	}

}
