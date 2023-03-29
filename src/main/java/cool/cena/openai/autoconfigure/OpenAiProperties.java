package cool.cena.openai.autoconfigure;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="openai")
public class OpenAiProperties {

    private String key, organization;
    private OpenAiTextCompletionProperties textCompletion = new OpenAiTextCompletionProperties();
    private OpenAiChatCompletionProperties chatCompletion = new OpenAiChatCompletionProperties();
    private OpenAiModerationProperties moderation = new OpenAiModerationProperties();

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
    
    public OpenAiTextCompletionProperties getTextCompletion() {
        return textCompletion;
    }

    public void setTextCompletion(OpenAiTextCompletionProperties textCompletion) {
        this.textCompletion = textCompletion;
    }

    public OpenAiChatCompletionProperties getChatCompletion() {
        return chatCompletion;
    }
    
    public void setChatCompletion(OpenAiChatCompletionProperties chatCompletion) {
        this.chatCompletion = chatCompletion;
    }

    public OpenAiModerationProperties getModeration() {
        return moderation;
    }

    public void setModeration(OpenAiModerationProperties moderation) {
        this.moderation = moderation;
    }


    public static class OpenAiTextCompletionProperties{

        private String model = "text-davinci-003";
        private String suffix;

        @DecimalMin("0")
        @DecimalMax("2")
        private Double temperature;
        private Double topP;
    
        private Integer n, maxTokens;
        private boolean stream, echo;

        @Min(0)
        @Max(5)
        private Integer logprobs;
        private List<String> stop;

        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private Double presencePenalty;
        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private Double frequencyPenalty;

        @Min(0)
        @Max(20)
        private Integer bestOf;
        private Map<Integer, Double> logitBias;
        private String user;

        public String getModel() {
            return model;
        }
        public void setModel(String model) {
            this.model = model;
        }
        public String getSuffix() {
            return suffix;
        }
        public void setSuffix(String suffix) {
            this.suffix = suffix;
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
        public Integer getMaxTokens() {
            return maxTokens;
        }
        public void setMaxTokens(Integer maxTokens) {
            this.maxTokens = maxTokens;
        }
        public Integer getLogprobs() {
            return logprobs;
        }
        public void setLogprobs(Integer logprobs) {
            this.logprobs = logprobs;
        }
        public boolean isStream() {
            return stream;
        }
        public void setStream(boolean stream) {
            this.stream = stream;
        }
        public boolean isEcho() {
            return echo;
        }
        public void setEcho(boolean echo) {
            this.echo = echo;
        }
        public List<String> getStop() {
            return stop;
        }
        public void setStop(List<String> stop) {
            this.stop = stop;
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
        public Integer getBestOf() {
            return bestOf;
        }
        public void setBestOf(Integer bestOf) {
            this.bestOf = bestOf;
        }
        public Map<Integer, Double> getLogitBias() {
            return logitBias;
        }
        public void setLogitBias(Map<Integer, Double> logitBias) {
            this.logitBias = logitBias;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
        
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
        private Double presencePenalty;
        @DecimalMin("-2.0")
        @DecimalMax("2.0")
        private Double frequencyPenalty;

        private Map<Integer, Double> logitBias;
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
        public Map<Integer, Double> getLogitBias() {
            return logitBias;
        }
        public void setLogitBias(Map<Integer, Double> logitBias) {
            this.logitBias = logitBias;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
    }

    public static class OpenAiModerationProperties{

        private String model;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    
    
    }
    




    
    
}
