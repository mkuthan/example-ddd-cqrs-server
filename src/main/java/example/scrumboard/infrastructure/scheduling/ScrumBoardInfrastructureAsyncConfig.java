package example.scrumboard.infrastructure.scheduling;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ScrumBoardInfrastructureAsyncConfig implements AsyncConfigurer {

	@Autowired
	@Qualifier("asyncExecutor")
	private Executor asyncExecutor;

	@Override
	public Executor getAsyncExecutor() {
		return asyncExecutor;
	}

}
