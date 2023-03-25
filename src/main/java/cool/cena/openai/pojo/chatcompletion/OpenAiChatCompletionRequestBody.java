package cool.cena.openai.pojo.chatcompletion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiChatCompletionRequestBody {
    private String model, user;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    @JsonProperty("max_tokens")
    private Integer maxCompletionToken;
    private List<OpenAiChatCompletionMessage> messages;

    public OpenAiChatCompletionRequestBody(OpenAiChatCompletionProperties chatCompletionProperties) {
        this.model = chatCompletionProperties.getModel();
        this.user = chatCompletionProperties.getUser();
        this.temperature = chatCompletionProperties.getTemperature();
        this.topP = chatCompletionProperties.getTopP();
        this.maxCompletionToken = chatCompletionProperties.getMaxCompletionToken();
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

    public List<OpenAiChatCompletionMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<OpenAiChatCompletionMessage> messages) {
        this.messages = messages;
    }
    
}
