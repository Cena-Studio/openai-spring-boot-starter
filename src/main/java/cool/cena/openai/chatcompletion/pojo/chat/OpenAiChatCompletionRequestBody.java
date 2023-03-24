package cool.cena.openai.chatcompletion.pojo.chat;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiChatCompletionRequestBody {
    private String model, user;
    private double temperature;
    @JsonProperty("top_p")
    private double topP;
    @JsonProperty("max_tokens")
    private int maxCompletionToken;
    private List<Message> messages;

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

    public int getMaxCompletionToken() {
        return maxCompletionToken;
    }

    public void setMaxCompletionToken(int maxCompletionToken) {
        this.maxCompletionToken = maxCompletionToken;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    
    
}
