package cool.cena.openai.autoconfigure;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="openai")
public class OpenAiProperties {

    private String key, organization;

    @DecimalMin("0")
    @DecimalMax("2")
    private double temperature = 1;
    private double nucleus = 1;

    private int single = 500;
    private int context = 500;
    private int completion = 500;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getNucleus() {
        return nucleus;
    }

    public void setNucleus(double nucleus) {
        this.nucleus = nucleus;
    }

    
    
}
