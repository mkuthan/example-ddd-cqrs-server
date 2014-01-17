package example.scrumboard.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ScrumBoardInfrastructureConfig {
}
