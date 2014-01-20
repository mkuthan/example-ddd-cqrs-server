package example.scrumboard.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;
import example.scrumboard.application.ScrumBoardApplicationConfig;
import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.jpa.ScrumBoardInfrastructureJpaConfig;
import example.scrumboard.infrastructure.rest.ScrumBoardInfrastructureRestConfig;
import example.scrumboard.rest.commands.ScrumBoardRestCommandsConfig;
import example.scrumboard.rest.queries.ScrumBoardRestQueriesConfig;

@Configuration
//@formatter:off
@Import({ 
	// external modules
	BootstrapConfig.class, 
	DddConfig.class,
	// infrastructure modules
	ScrumBoardInfrastructureJpaConfig.class,
	ScrumBoardInfrastructureRestConfig.class,
	// core modules
	ScrumBoardApplicationConfig.class,
	ScrumBoardDomainConfig.class,
	ScrumBoardRestCommandsConfig.class,
	ScrumBoardRestQueriesConfig.class,
})
//@formatter:on
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ScrumBoardConfig {

	@Bean
	@Profile("local")
	public DataSource localDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

}