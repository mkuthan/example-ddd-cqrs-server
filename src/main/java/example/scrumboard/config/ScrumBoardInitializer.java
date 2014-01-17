package example.scrumboard.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ScrumBoardInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ScrumBoardConfig.class);

		servletContext.addListener(new ContextLoaderListener(rootContext));

		ServletRegistration.Dynamic rest = servletContext.addServlet("rest", new DispatcherServlet(rootContext));
		rest.setLoadOnStartup(1);
		rest.addMapping("/rest/*");

		ServletRegistration.Dynamic h2 = servletContext.addServlet("h2", new WebServlet());
		h2.setLoadOnStartup(2);
		h2.addMapping("/h2/*");
	}

}
