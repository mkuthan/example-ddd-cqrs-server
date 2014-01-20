package example.scrumboard.infrastructure.jpa.repositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import example.ddd.DddConfig;
import example.scrumboard.config.ScrumBoardTestConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;

@ContextConfiguration(classes = { ScrumBoardInfrastructureJpaConfig.class, ScrumBoardTestConfig.class })
@ActiveProfiles({ ScrumBoardTestConfig.PROFILE, DddConfig.PROFILE })
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaRepositoryTests {
}