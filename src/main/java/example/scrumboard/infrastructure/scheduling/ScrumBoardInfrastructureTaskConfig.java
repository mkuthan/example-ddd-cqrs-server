package example.scrumboard.infrastructure.scheduling;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import example.scrumboard.config.ScrumBoardBeansConfig;

@Configuration
@EnableScheduling
@Profile({ ScrumBoardBeansConfig.Local.PROFILE, ScrumBoardBeansConfig.Remote.PROFILE })
public class ScrumBoardInfrastructureTaskConfig implements SchedulingConfigurer {

	@Autowired
	@Qualifier("taskExecutor")
	private Executor taskExecutor;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor);
	}

}
