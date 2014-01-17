package example.bootstrap.infrastructure;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BootstrapLoggingAspect {

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@AfterReturning(pointcut = "execution(public * example.ddd.domain.Repository+.load(..))", returning = "entity")
	public void autowireLoadedEntity(Object entity) {
		beanFactory.autowireBean(entity);
	}

}
