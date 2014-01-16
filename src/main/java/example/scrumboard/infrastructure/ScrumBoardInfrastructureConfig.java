package example.scrumboard.infrastructure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.ddd.infrastructure.DddInfrastructureConfig;
import groovy.sql.Sql;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({ DddInfrastructureConfig.class })
public class ScrumBoardInfrastructureConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2);
		return builder.build();
	}

	@Bean(destroyMethod = "close")
	@Autowired
	public Sql sql(DataSource dataSource) {
		return new Sql(dataSource);
	}

}
