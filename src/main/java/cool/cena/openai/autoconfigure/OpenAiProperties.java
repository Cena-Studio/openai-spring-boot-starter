package cool.cena.openai.autoconfigure;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.springframework.boot.context.properties.ConfigurationProperties;

/* @Configuration */
@ConfigurationProperties(prefix="openai")
public class OpenAiProperties {

    private String key, organization;
    private OpenAiChatCompletionProperties chatCompletion;

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
        private double temperature;
        private double topP;
    
        private double n;
        private boolean stream;

        private List<String> stop;

        private int maxPromptToken = 3000;
        private int maxCompletionToken;

        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private double presencePenalty;
        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private double frequencyPenalty;

        private Map<String, Integer> logitBias;
        private String user;
        
        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public double getTemperature() {
            return temperature;
        }
        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
        public double getTopP() {
            return topP;
        }
        public void setTopP(double topP) {
            this.topP = topP;
        }
        public double getN() {
            return n;
        }
        public void setN(double n) {
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
        public int getMaxPromptToken() {
            return maxPromptToken;
        }
        public void setMaxPromptToken(int maxPromptToken) {
            this.maxPromptToken = maxPromptToken;
        }
        public int getMaxCompletionToken() {
            return maxCompletionToken;
        }
        public void setMaxCompletionToken(int maxCompletionToken) {
            this.maxCompletionToken = maxCompletionToken;
        }
        public double getPresencePenalty() {
            return presencePenalty;
        }
        public void setPresencePenalty(double presencePenalty) {
            this.presencePenalty = presencePenalty;
        }
        public double getFrequencyPenalty() {
            return frequencyPenalty;
        }
        public void setFrequencyPenalty(double frequencyPenalty) {
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
