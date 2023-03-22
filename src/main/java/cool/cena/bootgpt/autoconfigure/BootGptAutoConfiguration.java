package cool.cena.bootgpt.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cool.cena.bootgpt.BootGptSource;

@Configuration
@EnableConfigurationProperties(BootGptAutoConfigurationProperties.class)
public class BootGptAutoConfiguration {
    
    @Autowired
    BootGptAutoConfigurationProperties bootGptAutoConfigurationProperties;

    @Bean
    @ConditionalOnProperty(prefix="bootgpt", name = "organization", havingValue = "ConditionalOnMissingProperty", matchIfMissing = true)
    public BootGptSource configureBootGptApiAccessor(){
        String httpHeaderAuthorization = "Bearer " + bootGptAutoConfigurationProperties.getKey();
        return new BootGptSource(httpHeaderAuthorization);
    }

    @Bean
    @ConditionalOnProperty(prefix="bootgpt", name = "organization")
    public BootGptSource configureBootGptApiAccessorWithOrganization(){
        String httpHeaderAuthorization = "Bearer " + bootGptAutoConfigurationProperties.getKey();
        String httpHeaderOpenAiOrganization = bootGptAutoConfigurationProperties.getOrganization();
        return new BootGptSource(httpHeaderAuthorization, httpHeaderOpenAiOrganization);
    }
}
