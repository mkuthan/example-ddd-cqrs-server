package example.scrumboard.infrastructure.jpa.spring;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RepositoryAutowiringAspect {

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	@AfterReturning(pointcut = "execution(public * example.ddd.Repository+.load(..))", returning = "aggregateRoot")
	public void autowireLoadedEntity(Object aggregateRoot) {
		beanFactory.autowireBean(aggregateRoot);
	}

}
