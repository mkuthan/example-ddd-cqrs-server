package example.scrumboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ScrumBoardBeansConfig.class, ScrumBoardModulesConfig.class })
public class ScrumBoardConfig {
}