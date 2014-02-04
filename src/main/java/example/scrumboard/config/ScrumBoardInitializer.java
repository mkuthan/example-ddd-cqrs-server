package example.scrumboard.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class ScrumBoardInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "rest";
	private static final String H2_SERVLET_NAME = "h2";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ScrumBoardConfig.class);

		servletContext.addListener(new ContextLoaderListener(rootContext));

		registerDispatcherServlet(servletContext, rootContext);
		registerH2WebServlet(servletContext);

		registerHttpPutContentFilter(servletContext);
		registerSpringSecurityFilter(servletContext);
	}

	private void registerDispatcherServlet(ServletContext servletContext, WebApplicationContext rootContext) {
		ServletRegistration.Dynamic rest = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(
				rootContext));
		rest.setLoadOnStartup(1);
		rest.addMapping("/rest/*");
	}

	private void registerH2WebServlet(ServletContext servletContext) {
		ServletRegistration.Dynamic h2 = servletContext.addServlet(H2_SERVLET_NAME, new WebServlet());
		h2.setLoadOnStartup(2);
		h2.addMapping("/h2/*");
	}

	private void registerHttpPutContentFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic registration = servletContext.addFilter("httpPutFormContentFilter",
				HttpPutFormContentFilter.class);
		registration.addMappingForServletNames(getDispatcherTypes(), false, DISPATCHER_SERVLET_NAME);
	}

	private void registerSpringSecurityFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic springSecurity = servletContext.addFilter("springSecurityFilterChain",
				new DelegatingFilterProxy("springSecurityFilterChain"));
		springSecurity.addMappingForServletNames(getDispatcherTypes(), false, DISPATCHER_SERVLET_NAME);
	}

	private EnumSet<DispatcherType> getDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
	}

}
