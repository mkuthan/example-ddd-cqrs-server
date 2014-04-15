package example.scrumboard.infrastructure.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;

@Configuration
@EnableIntegration
@ComponentScan
public class ScrumBoardInfrastructureEventsConfig {

	@Bean
	public PublishSubscribeChannel eventBus() {
		return new PublishSubscribeChannel();
	}

	@Bean
	public MessagingTemplate messagingTemplate(PublishSubscribeChannel eventBus) {
		MessagingTemplate messagingTemplate = new MessagingTemplate();
		messagingTemplate.setDefaultChannel(eventBus);

		return messagingTemplate;
	}

}
