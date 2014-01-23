package example.bootstrap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(BootstrapConfig.PROFILE)
@ComponentScan
public class BootstrapConfig {

	public static final String PROFILE = "bootstrap";
	
}
