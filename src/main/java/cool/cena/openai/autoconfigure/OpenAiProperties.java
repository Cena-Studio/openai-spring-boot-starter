package cool.cena.openai.autoconfigure;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="openai")
public class OpenAiProperties {

    private String key, organization;
    private OpenAiChatCompletionProperties chatCompletion = new OpenAiChatCompletionProperties();

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

    public OpenAiChatCompletionProperties getChatCompletion() {
        return chatCompletion;
    }
    
    public void setChatCompletion(OpenAiChatCompletionProperties chatCompletion) {
        this.chatCompletion = chatCompletion;
    }

    public static class OpenAiChatCompletionProperties{

        private String model = "gpt-3.5-turbo";
        
        @DecimalMin("0")
        @DecimalMax("2")
        private Double temperature;
        private Double topP;
    
        private Integer n;
        private boolean stream;

        private List<String> stop;

        private Integer maxPromptToken = 3000;
        private Integer maxCompletionToken;

        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private Double presencePenalty = 0.0;
        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private Double frequencyPenalty = 0.0;

        private Map<String, Integer> logitBias;
        private String user;
        
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public Double getTemperature() {
            return temperature;
        }
        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }
        public Double getTopP() {
            return topP;
        }
        public void setTopP(Double topP) {
            this.topP = topP;
        }
        public Integer getN() {
            return n;
        }
        public void setN(Integer n) {
            this.n = n;
        }
        public boolean isStream() {
            return stream;
        }
        public void setStream(boolean stream) {
            this.stream = stream;
        }
        public List<String> getStop() {
            return stop;
        }
        public void setStop(List<String> stop) {
            this.stop = stop;
        }
        public Integer getMaxPromptToken() {
            return maxPromptToken;
        }
        public void setMaxPromptToken(Integer maxPromptToken) {
            this.maxPromptToken = maxPromptToken;
        }
        public Integer getMaxCompletionToken() {
            return maxCompletionToken;
        }
        public void setMaxCompletionToken(Integer maxCompletionToken) {
            this.maxCompletionToken = maxCompletionToken;
        }
        public Double getPresencePenalty() {
            return presencePenalty;
        }
        public void setPresencePenalty(Double presencePenalty) {
            this.presencePenalty = presencePenalty;
        }
        public Double getFrequencyPenalty() {
            return frequencyPenalty;
        }
        public void setFrequencyPenalty(Double frequencyPenalty) {
            this.frequencyPenalty = frequencyPenalty;
        }
        public Map<String, Integer> getLogitBias() {
            return logitBias;
        }
        public void setLogitBias(Map<String, Integer> logitBias) {
            this.logitBias = logitBias;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }




    

    }




    
    
}
