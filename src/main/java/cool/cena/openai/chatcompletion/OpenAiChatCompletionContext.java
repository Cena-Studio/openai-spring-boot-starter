package cool.cena.openai.chatcompletion;

import java.util.ArrayList;
import java.util.List;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.chatcompletion.pojo.Segment;
import cool.cena.openai.chatcompletion.pojo.chat.Message;
import cool.cena.openai.chatcompletion.pojo.chat.OpenAiChatCompletionResponse;

public class OpenAiChatCompletionContext {

    private OpenAiApiAccessor opanAiApiAccessor;

    private int contextVersion;

    private List<Message> contextMessages;
    private Message lastContextMessage;
    
    private List<Segment> segments;
    private int segmentSize, cumulativeToken, maxPromptToken, maxCompletionToken;

    public OpenAiChatCompletionContext(OpenAiApiAccessor opanAiApiAccessor) {
        this.opanAiApiAccessor = opanAiApiAccessor;

        this.contextVersion = 0;

        this.contextMessages = new ArrayList<>();
        
        this.segments = new ArrayList<>();
        this.segmentSize = 0;
        this.cumulativeToken = 0;
        this.maxPromptToken = 2048;

    }

    private void addMessage(Message newMessage){
        if(this.lastContextMessage != null && this.lastContextMessage.hasSameRole(newMessage)){
            this.lastContextMessage.merge(newMessage);
        }else{
            this.contextMessages.add(newMessage);
            this.lastContextMessage = newMessage;
            this.segmentSize++;
        }
    }

    public OpenAiChatCompletionContext addSystemMessage(String newMessageContent){
        Message newMessage = new Message("system", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionContext addUserMessage(String newMessageContent){
        Message newMessage = new Message("user", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionContext addAssistantMessage(String newMessageContent){
        Message newMessage = new Message("assistant", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public List<Message> getContextMessages(){
        return this.contextMessages;
    }

    public OpenAiChatCompletionResponse createChatCompletion(){
        int requestContextVersion = ++this.contextVersion;

        OpenAiChatCompletionResponse opanAiChatCompletionResponse = this.opanAiApiAccessor.sendRequest(this.contextMessages);

        // the following lines execute after the response from opanAiApiAccessor received
        // current context is the latest context
        if(requestContextVersion == this.contextVersion){
            
            int responseBodyStatus = opanAiChatCompletionResponse.getStatus();
            opanAiChatCompletionResponse.setStatus(responseBodyStatus);

            // normal response
            if (responseBodyStatus == 200) {

                System.out.println("Request " + requestContextVersion + " success. Message: " + opanAiChatCompletionResponse.getObjectMessage().getContent());

                // token process
                int responsePromptToken = opanAiChatCompletionResponse.getPromptToken();
                int segmentToken = responsePromptToken - this.cumulativeToken;
                Segment promptSegment = new Segment(segmentSize, segmentToken);
                segments.add(promptSegment);

                System.out.println("ps added. size: " + segmentSize + " token: " + segmentToken);
        
                int responseCompletionToken = opanAiChatCompletionResponse.getCompletionToken();
                Segment completionSegment = new Segment(1, responseCompletionToken);
                segments.add(completionSegment);
    
                System.out.println("cs added. size: " + 1 + " token: " + responseCompletionToken);

                this.cumulativeToken = responsePromptToken + responseCompletionToken;

                Message responseMessage = opanAiChatCompletionResponse.getObjectMessage();
                this.addMessage(responseMessage);

                this.segmentSize = 0;

                System.out.println("cumulative token: " + this.cumulativeToken);

                while(this.cumulativeToken > this.maxPromptToken){
                    this.reduceContext();
                }
            
                return opanAiChatCompletionResponse;

            }

            // request error
            System.out.println("Request " + requestContextVersion + " error: " + opanAiChatCompletionResponse.getErrMessage());
    
            return opanAiChatCompletionResponse;

        }

        // context has been updated during the request
        System.out.println("Context outdated. Request " + requestContextVersion + " has been deprecated.");
        opanAiChatCompletionResponse.setStatus(900);
        opanAiChatCompletionResponse.setErrMessage("This request has been deprecated because it has been superseded by a new request.");

        return opanAiChatCompletionResponse;
        
    }

    public OpenAiChatCompletionResponse createChatCompletion(String newMessageContent){
        return this.addUserMessage(newMessageContent).createChatCompletion();
    }

    // token management
    public void reduceContext(){
        int segmentSize = this.segments.get(0).getSize();
        int segmentToken = this.segments.get(0).getToken();
        this.contextMessages.subList(0, segmentSize).clear();
        this.segments.remove(0);
        this.cumulativeToken -= segmentToken;

        System.out.println("segment removed. current token: " + this.cumulativeToken);
    }
}
