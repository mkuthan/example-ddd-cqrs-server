package example.scrumboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.bootstrap.BootstrapConfig;
import example.ddd.DddConfig;

@Configuration
@Import({ BootstrapConfig.class, DddConfig.class })
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ScrumBoardCommonConfig {
}
