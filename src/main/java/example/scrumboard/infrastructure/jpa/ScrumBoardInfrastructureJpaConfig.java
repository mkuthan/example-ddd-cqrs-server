package example.scrumboard.infrastructure.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.jpa.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import example.scrumboard.domain.ScrumBoardDomainConfig;
import example.scrumboard.infrastructure.jpa.hibernate.FixedPrefixNamingStrategy;

@Configuration
@ComponentScan
public class ScrumBoardInfrastructureJpaConfig {

	@Autowired
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

		jpaVendorAdapter.setDatabase(Database.H2);
		jpaVendorAdapter.setGenerateDdl(environment.getRequiredProperty("jpa.generateDdl", Boolean.class));
		jpaVendorAdapter.setShowSql(environment.getRequiredProperty("jpa.showSql", Boolean.class));

		Map<String, Object> jpaProperties = new HashMap<>();
		jpaProperties.put(AvailableSettings.NAMING_STRATEGY, FixedPrefixNamingStrategy.class.getName());

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(ScrumBoardDomainConfig.class.getPackage().getName());
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
