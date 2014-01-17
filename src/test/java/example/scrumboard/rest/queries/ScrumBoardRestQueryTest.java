package example.scrumboard.rest.queries;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import example.scrumboard.config.ScrumBoardConfig;
import example.scrumboard.rest.config.ScrumBoardRestCommonConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { ScrumBoardConfig.class, ScrumBoardRestCommonConfig.class })
@ActiveProfiles({ "test", "bootstrap" })
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScrumBoardRestQueryTest {
}