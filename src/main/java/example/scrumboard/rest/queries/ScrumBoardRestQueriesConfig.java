package example.scrumboard.rest.queries;

import groovy.sql.Sql;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableWebMvc
@EnableSpringDataWebSupport
public class ScrumBoardRestQueriesConfig {

	@Bean(destroyMethod = "close")
	@Autowired
	public Sql sql(DataSource dataSource) {
		return new Sql(dataSource);
	}

}
