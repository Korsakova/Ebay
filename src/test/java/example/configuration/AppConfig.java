package example.configuration;

import example.common.configuration.WebdriverConfig;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({WebdriverConfig.class})
@ComponentScan("example.common") // Configurations
@ComponentScan("example.pages") // PageObjects
@ComponentScan("example.steps") // Steps of PO
@PropertySources({
        @PropertySource("classpath:env/${NG_ENV:dev}.properties"),
        @PropertySource("classpath:core/webdrivermanager.properties")
})
public class AppConfig {


}
