package example.scrumboard.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

// http://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-security

@Configuration
@EnableWebSecurity
@ComponentScan
public class ScrumBoardInfrastructureSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private DefaultPermissionEvaluator permissionEvaluator;

	@Bean
	public UsernamePasswordAuthenticationFilter loginFilter() throws Exception {
		UsernamePasswordAuthenticationFilter loginFilter = new UsernamePasswordAuthenticationFilter();

		loginFilter.setAuthenticationManager(authenticationManager());
		loginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		loginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		loginFilter.setFilterProcessesUrl("/rest/login");
		loginFilter.setUsernameParameter("username");
		loginFilter.setPasswordParameter("password");
		loginFilter.setPostOnly(true);

		return loginFilter;
	}

	public SecurityExpressionHandler expressionHandler() {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(permissionEvaluator);
		return expressionHandler;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.expressionHandler(expressionHandler());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("test").password("secret").authorities("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()
			.addFilter(loginFilter())
			.logout()
				.logoutUrl("/rest/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
				.and()
			.csrf().disable(); // TODO: enable
		// @formatter:on
	}
}
