package cool.cena.openai.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cool.cena.openai.OpenAiSource;

@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiAutoConfiguration {
    
    @Autowired
    OpenAiProperties bootGptAutoConfigurationProperties;

    @Bean
    @ConditionalOnProperty(prefix="openai", name = "organization", havingValue = "ConditionalOnMissingProperty", matchIfMissing = true)
    public OpenAiSource configureBootGptApiAccessor(){
        String httpHeaderAuthorization = "Bearer " + bootGptAutoConfigurationProperties.getKey();
        return new OpenAiSource(httpHeaderAuthorization);
    }

    @Bean
    @ConditionalOnProperty(prefix="openai", name = "organization")
    public OpenAiSource configureBootGptApiAccessorWithOrganization(){
        String httpHeaderAuthorization = "Bearer " + bootGptAutoConfigurationProperties.getKey();
        String httpHeaderOpenAiOrganization = bootGptAutoConfigurationProperties.getOrganization();
        return new OpenAiSource(httpHeaderAuthorization, httpHeaderOpenAiOrganization);
    }
}
