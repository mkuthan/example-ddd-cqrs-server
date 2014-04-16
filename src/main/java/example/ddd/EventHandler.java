package example.ddd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.integration.annotation.ServiceActivator;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ServiceActivator(inputChannel = "eventBus")
public @interface EventHandler {
}