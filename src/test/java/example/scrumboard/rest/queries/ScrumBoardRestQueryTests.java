package example.scrumboard.rest.queries;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;
import example.scrumboard.config.ScrumBoardConfig;
import example.scrumboard.config.ScrumBoardTestConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { ScrumBoardTestConfig.class, ScrumBoardConfig.class })
@ActiveProfiles({ ScrumBoardTestConfig.PROFILE, BootstrapConfig.PROFILE, DddConfig.PROFILE })
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ScrumBoardRestQueryTests {
}