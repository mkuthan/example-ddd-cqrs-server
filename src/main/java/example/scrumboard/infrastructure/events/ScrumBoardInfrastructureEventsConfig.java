package example.scrumboard.infrastructure.events;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:/activemq.xml", "classpath:/domain-events.xml" })
@ComponentScan
public class ScrumBoardInfrastructureEventsConfig {
}
