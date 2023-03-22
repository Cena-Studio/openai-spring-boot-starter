package cool.cena.bootgpt;

import java.util.ArrayList;
import java.util.List;

import cool.cena.bootgpt.pojo.chat.Message;
import cool.cena.bootgpt.pojo.chat.ResponseBody;

public class BootGptContext {

    private List<Message> contextMessages;
    private int contextSize, responsePointer;
    private BootGptApiAccessor bootGptApiAccessor;

    public BootGptContext(BootGptApiAccessor bootGptApiAccessor) {
        this.contextMessages = new ArrayList<>();
        this.contextSize = 0;
        this.bootGptApiAccessor = bootGptApiAccessor;
    }

    private void addMessage(Message message){
        this.contextMessages.add(message);
        this.contextSize += 1;
    }

    public BootGptContext addSystemMessage(String newMessageContent){
        Message newMessage = new Message("system", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public BootGptContext addUserMessage(String newMessageContent){
        Message newMessage = new Message("user", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public BootGptContext addAssistantMessage(String newMessageContent){
        Message newMessage = new Message("assistant", newMessageContent);
        this.addMessage(newMessage);;
        return this;
    }

    public List<Message> getContextMessages(){
        return this.contextMessages;
    }

    public String getLastResponseMessage(){
        return this.contextMessages.get(this.responsePointer).getContent();
    }

    public BootGptContext sendMessage(){
        ResponseBody responseBody = this.bootGptApiAccessor.sendRequest(this.contextMessages);
        Message responseMessage = responseBody.getResponseMessage();
        this.addMessage(responseMessage);
        this.responsePointer = this.contextSize - 1;
        return this;
    }

    public BootGptContext sendMessage(String newMessageContent){
        this.addUserMessage(newMessageContent);
        this.sendMessage();
        return this;
    }
}
