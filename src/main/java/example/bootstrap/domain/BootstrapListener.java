package example.bootstrap.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import example.bootstrap.BootstrapConfig;

@Component
@Profile(BootstrapConfig.PROFILE)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BootstrapListener {
}