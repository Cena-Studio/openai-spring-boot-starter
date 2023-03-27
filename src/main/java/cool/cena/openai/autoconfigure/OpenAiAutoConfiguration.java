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
    OpenAiProperties properties;

    @Bean
    @ConditionalOnProperty(prefix="openai", name = "organization", havingValue = "ConditionalOnMissingProperty", matchIfMissing = true)
    public OpenAiSource configureOpenAiApiAccessor(){
        String httpHeaderAuthorization = "Bearer " + properties.getKey();
        return new OpenAiSource(httpHeaderAuthorization, this.properties.getChatCompletion());
    }

    @Bean
    @ConditionalOnProperty(prefix="openai", name = "organization")
    public OpenAiSource configureOpenAiApiAccessorWithOrganization(){
        String httpHeaderAuthorization = "Bearer " + properties.getKey();
        String httpHeaderOpenAiOrganization = properties.getOrganization();
        return new OpenAiSource(httpHeaderAuthorization, httpHeaderOpenAiOrganization);
    }
}
