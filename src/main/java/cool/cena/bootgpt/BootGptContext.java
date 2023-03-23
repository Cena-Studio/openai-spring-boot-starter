package cool.cena.bootgpt;

import java.util.ArrayList;
import java.util.List;

import cool.cena.bootgpt.pojo.TokenSegment;
import cool.cena.bootgpt.pojo.chat.Message;
import cool.cena.bootgpt.pojo.chat.ResponseBody;

public class BootGptContext {

    private BootGptApiAccessor bootGptApiAccessor;

    private List<Message> contextMessages;
    private String lastResponseMessage;
    
    private List<TokenSegment> tokenSegments;
    private int currentSegementSize, cumulativeToken;

    public BootGptContext(BootGptApiAccessor bootGptApiAccessor) {
        this.bootGptApiAccessor = bootGptApiAccessor;

        this.contextMessages = new ArrayList<>();
        this.lastResponseMessage = "";
        
        this.tokenSegments = new ArrayList<>();
        this.currentSegementSize = 0;
        this.cumulativeToken = 0;

    }

    private void addMessage(Message message){
        this.contextMessages.add(message);
        this.currentSegementSize += 1;
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
        this.addMessage(newMessage);
        return this;
    }

    public List<Message> getContextMessages(){
        return this.contextMessages;
    }

    public String getLastResponseMessage(){
        return this.lastResponseMessage;
    }

    public BootGptContext sendMessage(){

        ResponseBody responseBody = this.bootGptApiAccessor.sendRequest(this.contextMessages);

        if (responseBody.isNormal()) {

            Message responseMessage = responseBody.getResponseMessage();
            this.addMessage(responseMessage);
            this.lastResponseMessage = responseMessage.getContent();
    
            int responsePromptToken = responseBody.getUsage().getPromptTokens();
            int currentSegmentToken = responsePromptToken - this.cumulativeToken;
            TokenSegment promptTokenSegment = new TokenSegment(currentSegementSize, currentSegmentToken);
            tokenSegments.add(promptTokenSegment);
    
            int responseCompletionToken = responseBody.getUsage().getCompletionTokens();
            TokenSegment completionTokenSegment = new TokenSegment(1, responseCompletionToken);
            tokenSegments.add(completionTokenSegment);
    
            this.currentSegementSize = 0;
            this.cumulativeToken = responsePromptToken + responseCompletionToken;
            
        }else if(responseBody.getStatus() == 400){

            

        }else{



        }

        return this;
    }

    public BootGptContext sendMessage(String newMessageContent){
        this.addUserMessage(newMessageContent);
        this.sendMessage();
        return this;
    }
}
