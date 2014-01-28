package example.scrumboard.infrastructure.jpa;

import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import example.ddd.EventPublisher;
import example.scrumboard.infrastructure.shared.ScrumBoardInfrastructureTransactionConfig;

@Import({ ScrumBoardInfrastructureJpaConfig.class, ScrumBoardInfrastructureTransactionConfig.class })
@PropertySource("classpath:/test.properties")
public class JpaTestConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public EventPublisher PublishSubscribeChannel() {
		return Mockito.mock(EventPublisher.class);
	}

}