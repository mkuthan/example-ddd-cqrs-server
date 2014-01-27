package example.scrumboard.rest.queries;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import example.scrumboard.config.ScrumBoardBeansTestConfig;
import example.scrumboard.config.ScrumBoardModulesConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { ScrumBoardBeansTestConfig.class, ScrumBoardModulesConfig.class })
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScrumBoardRestQueryTests {
}
