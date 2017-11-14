package jpaspring;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(ConfigInfrastructure.class)
public class ConfigApplication {
}
