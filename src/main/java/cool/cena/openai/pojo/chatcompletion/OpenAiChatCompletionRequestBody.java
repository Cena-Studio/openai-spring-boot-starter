package cool.cena.openai.pojo.chatcompletion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiChatCompletionRequestBody {
    private String model, user;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    private Integer n;
    private List<String> stop;
    @JsonProperty("max_tokens")
    private Integer maxCompletionToken;
    @JsonProperty("presence_penalty")
    private Double presencePenalty;
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;
    @JsonProperty("logit_bias")
    Map<Integer, Double> logitBias;
    private List<ChatCompletionMessage> messages;

    public OpenAiChatCompletionRequestBody(OpenAiChatCompletionProperties chatCompletionProperties) {
        this.model = chatCompletionProperties.getModel();
        this.user = chatCompletionProperties.getUser();
        this.temperature = chatCompletionProperties.getTemperature();
        this.topP = chatCompletionProperties.getTopP();
        this.n = chatCompletionProperties.getN();
        this.stop = chatCompletionProperties.getStop();
        this.maxCompletionToken = chatCompletionProperties.getMaxCompletionToken();
        this.presencePenalty = chatCompletionProperties.getPresencePenalty();
        this.frequencyPenalty = chatCompletionProperties.getFrequencyPenalty();
        this.logitBias = chatCompletionProperties.getLogitBias();
        this.messages = new ArrayList<>();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public Integer getMaxCompletionToken() {
        return maxCompletionToken;
    }

    public void setMaxCompletionToken(Integer maxCompletionToken) {
        this.maxCompletionToken = maxCompletionToken;
    }

    public List<ChatCompletionMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatCompletionMessage> messages) {
        this.messages = messages;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
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

    public Map<Integer, Double> getLogitBias() {
        return logitBias;
    }

    public void setLogitBias(Map<Integer, Double> logitBias) {
        this.logitBias = logitBias;
    }
    
    
}
