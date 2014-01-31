package example.scrumboard.application.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SampleTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleTask.class);

	@Scheduled(fixedRate = 60000)
	public void log() {
		LOGGER.info("Task performed");
	}

}
