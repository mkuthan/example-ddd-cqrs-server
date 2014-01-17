package example.scrumboard.rest.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import example.scrumboard.rest.config.ScrumBoardRestCommonConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { ScrumBoardRestCommandsConfig.class, ScrumBoardRestCommonConfig.class })
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScrumBoardRestCommandTest {
}