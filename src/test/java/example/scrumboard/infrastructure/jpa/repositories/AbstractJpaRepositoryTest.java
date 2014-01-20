package example.scrumboard.infrastructure.jpa.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test(singleThreaded = true)
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager entityManager;

	protected void clear() {
		entityManager.clear();
	}

	@Configuration
	public static class Config {

		@Bean
		@Profile("jpa_tests")
		public DataSource testDataSource() {
			return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
		}

	}
}
