package example.scrumboard.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.scrumboard.rest.commands.ScrumBoardRestCommandsConfig;
import example.scrumboard.rest.queries.ScrumBoardRestQueriesConfig;

@Configuration
//@formatter:off
@Import({ 
	ScrumBoardRestCommonConfig.class,
	ScrumBoardRestCommandsConfig.class, 
	ScrumBoardRestQueriesConfig.class 
})
//@formatter:on
public class ScrumBoardRestConfig {
}
