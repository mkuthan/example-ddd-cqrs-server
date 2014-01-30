package example.scrumboard.rest.queries;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import example.scrumboard.infrastructure.rest.ScrumBoardInfrastructureRestConfig;

@Import({ ScrumBoardInfrastructureRestConfig.class, ScrumBoardRestQueriesConfig.class })
@PropertySource("classpath:/test.properties")
public class RestQueryTestConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.setName(RestQueryTestConfig.class.getSimpleName()).addScript("sample.sql").build();
	}
}
