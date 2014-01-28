package example.bootstrap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class BootstrapEvent extends ApplicationContextEvent {

	private static final long serialVersionUID = 1L;

	public BootstrapEvent(ApplicationContext source) {
		super(source);
	}

}
