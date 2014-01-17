package example.scrumboard.infrastructure.jpa.repositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import example.scrumboard.config.ScrumBoardCommonConfig;
import example.scrumboard.config.ScrumBoardDataSourcesConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;

@ContextConfiguration(classes = { ScrumBoardInfrastructureJpaConfig.class, ScrumBoardDataSourcesConfig.class,
		ScrumBoardCommonConfig.class })
@ActiveProfiles("test")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaRepositoryTest {
}