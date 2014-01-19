package example.scrumboard.rest.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import example.scrumboard.config.ScrumBoardRestCommonConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { ScrumBoardRestCommonConfig.class, ScrumBoardRestCommandsConfig.class })
@ActiveProfiles("mock")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScrumBoardRestCommandTests {
}