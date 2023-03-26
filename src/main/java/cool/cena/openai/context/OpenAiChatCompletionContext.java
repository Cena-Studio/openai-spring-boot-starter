package cool.cena.openai.context;

import java.util.ArrayList;
import java.util.List;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;
import cool.cena.openai.pojo.chatcompletion.ChatCompletionMessage;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionRequestBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponse;

public class OpenAiChatCompletionContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiChatCompletionRequestBody requestBody;

    private int contextVersion;

    private ChatCompletionMessage lastContextMessage;
    
    private List<Segment> segments;
    private int segmentSize, cumulativeToken, maxPromptToken;

    public OpenAiChatCompletionContext(OpenAiApiAccessor apiAccessor, OpenAiChatCompletionProperties openAiChatCompletionProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiChatCompletionRequestBody(openAiChatCompletionProperties);

        this.contextVersion = 0;
        
        this.segments = new ArrayList<>();
        this.segmentSize = 0;
        this.cumulativeToken = 0;
        this.maxPromptToken = openAiChatCompletionProperties.getMaxPromptToken();

    }

    private void addMessage(ChatCompletionMessage newMessage){
        if(this.lastContextMessage != null && this.lastContextMessage.hasSameRole(newMessage)){
            this.lastContextMessage.merge(newMessage);
        }else{
            this.requestBody.getMessages().add(newMessage);
            this.lastContextMessage = newMessage;
            this.segmentSize++;
        }
    }

    public OpenAiChatCompletionContext addSystemMessage(String newMessageContent){
        ChatCompletionMessage newMessage = new ChatCompletionMessage("system", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionContext addUserMessage(String newMessageContent){
        ChatCompletionMessage newMessage = new ChatCompletionMessage("user", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionContext addAssistantMessage(String newMessageContent){
        ChatCompletionMessage newMessage = new ChatCompletionMessage("assistant", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionResponse create(){
        int requestContextVersion = ++this.contextVersion;

        OpenAiChatCompletionResponse response = this.apiAccessor.sendChatCompletionRequest(requestBody);

        // the following lines execute after the response from opanAiApiAccessor received
        // current context is the latest context
        if(requestContextVersion == this.contextVersion){
            
            int responseBodyStatus = response.getStatus();
            response.setStatus(responseBodyStatus);

            // normal response
            if (responseBodyStatus == 200) {

                System.out.println("Request " + requestContextVersion + " success. Message: " + response.getObjectMessage().getContent());

                // token process
                int responsePromptToken = response.getPromptToken();
                int segmentToken = responsePromptToken - this.cumulativeToken;
                Segment promptSegment = new Segment(segmentSize, segmentToken);
                segments.add(promptSegment);

                System.out.println("ps added. size: " + segmentSize + " token: " + segmentToken);
        
                int responseCompletionToken = response.getCompletionToken();
                Segment completionSegment = new Segment(1, responseCompletionToken);
                segments.add(completionSegment);
    
                System.out.println("cs added. size: " + 1 + " token: " + responseCompletionToken);

                this.cumulativeToken = responsePromptToken + responseCompletionToken;

                ChatCompletionMessage responseMessage = response.getObjectMessage();
                this.addMessage(responseMessage);

                this.segmentSize = 0;

                System.out.println("cumulative token: " + this.cumulativeToken);

                while(this.cumulativeToken > this.maxPromptToken){
                    this.reduceContext();
                }
            
                return response;

            }

            // request error
            System.out.println("Request " + requestContextVersion + " error: " + response.getErrMessage());
    
            return response;

        }

        // context has been updated during the request
        System.out.println("Context outdated. Request " + requestContextVersion + " has been deprecated.");
        response.setStatus(900);
        response.setErrMessage("This request has been deprecated because it has been superseded by a new request.");

        return response;
        
    }

    public OpenAiChatCompletionResponse create(String newMessageContent){
        return this.addUserMessage(newMessageContent).create();
    }

    // token management
    public void reduceContext(){
        int segmentSize = this.segments.get(0).getSize();
        int segmentToken = this.segments.get(0).getToken();
        this.requestBody.getMessages().subList(0, segmentSize).clear();
        this.segments.remove(0);
        this.cumulativeToken -= segmentToken;

        System.out.println("segment removed. current token: " + this.cumulativeToken);
    }

    private static class Segment {

        private int size, token;
        
        public Segment(int size, int token){
            this.size = size;
            this.token = token;
        }
    
        public int getSize() {
            return size;
        }
    
        public int getToken() {
            return token;
        }
        
    }
}
