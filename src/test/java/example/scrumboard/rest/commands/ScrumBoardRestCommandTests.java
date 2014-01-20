package example.scrumboard.rest.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import example.scrumboard.config.ScrumBoardTestConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { ScrumBoardRestCommandsConfig.class, ScrumBoardTestConfig.class })
@ActiveProfiles({ ScrumBoardTestConfig.PROFILE })
@EnableSpringDataWebSupport
@EnableWebMvc
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScrumBoardRestCommandTests {
}