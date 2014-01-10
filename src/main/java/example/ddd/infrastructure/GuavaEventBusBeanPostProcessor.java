package example.ddd.infrastructure;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@Component
public class GuavaEventBusBeanPostProcessor implements BeanPostProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(GuavaEventBusBeanPostProcessor.class);

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private EventBus eventBus;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// to avoid memory leaks register only singletons
		if (beanFactory.containsBean(beanName) && beanFactory.isSingleton(beanName)) {
			eventBus.register(bean);
		} else {
			if (containsSubscribe(bean)) {
				LOGGER.warn("Bean {} was not registered with EventBus, only singleton beans can be registered.");
			}
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// do nothing
		return bean;
	}

	public static boolean containsSubscribe(Object bean) {
		Method[] methods = bean.getClass().getMethods();
		for (Method method : methods) {
			Subscribe subscribe = method.getAnnotation(Subscribe.class);
			if (subscribe != null) {
				return true;
			}
		}
		return false;
	}

}