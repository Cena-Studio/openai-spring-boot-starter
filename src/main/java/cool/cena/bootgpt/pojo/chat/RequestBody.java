package cool.cena.bootgpt.pojo.chat;

import java.util.List;

public class RequestBody {
    private String model;
    private List<Message> messages;

    public RequestBody(List<Message> messages){
        this.model = "gpt-3.5-turbo";
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    
}
