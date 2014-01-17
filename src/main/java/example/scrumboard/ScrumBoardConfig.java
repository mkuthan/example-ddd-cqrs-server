package example.scrumboard;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;

@Configuration
@Import({ BootstrapConfig.class, DddConfig.class })
@ComponentScan
public class ScrumBoardConfig {

	@Bean
	@Profile("local")
	public DataSource localDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	@Profile("test")
	public DataSource testDataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

}