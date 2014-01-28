package example.scrumboard.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class ScrumBoardBeansConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Configuration
	@PropertySource("classpath:/local.properties")
	@Profile(Local.PROFILE)
	public static class Local {

		public static final String PROFILE = "local";

		@Bean
		public DataSource localDataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
		}

	}

	@Configuration
	@PropertySource("classpath:/remote.properties")
	@Profile(Remote.PROFILE)
	public static class Remote {

		public static final String PROFILE = "remote";

		@Bean
		public DataSource remoteDataSource() {
			// TODO: JNDI lookup
			return null;
		}

	}

}
