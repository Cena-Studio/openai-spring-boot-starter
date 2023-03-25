package cool.cena.openai.chatcompletion;

import java.util.ArrayList;
import java.util.List;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.chatcompletion.pojo.OpenAiChatCompletionMessage;
import cool.cena.openai.chatcompletion.pojo.OpenAiChatCompletionRequestBody;
import cool.cena.openai.chatcompletion.pojo.OpenAiChatCompletionResponse;

public class OpenAiChatCompletionContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiChatCompletionRequestBody requestBody;

    private int contextVersion;

    private List<OpenAiChatCompletionMessage> contextMessages;
    private OpenAiChatCompletionMessage lastContextMessage;
    
    private List<Segment> segments;
    private int segmentSize, cumulativeToken, maxPromptToken;

    public OpenAiChatCompletionContext(OpenAiApiAccessor apiAccessor, OpenAiChatCompletionRequestBody requestBody) {
        this.apiAccessor = apiAccessor;
        this.requestBody = requestBody;

        this.contextVersion = 0;

        this.contextMessages = new ArrayList<>();
        
        this.segments = new ArrayList<>();
        this.segmentSize = 0;
        this.cumulativeToken = 0;
        this.maxPromptToken = 2048;

    }

    private void addMessage(OpenAiChatCompletionMessage newMessage){
        if(this.lastContextMessage != null && this.lastContextMessage.hasSameRole(newMessage)){
            this.lastContextMessage.merge(newMessage);
        }else{
            this.contextMessages.add(newMessage);
            this.lastContextMessage = newMessage;
            this.segmentSize++;
        }
    }

    public OpenAiChatCompletionContext addSystemMessage(String newMessageContent){
        OpenAiChatCompletionMessage newMessage = new OpenAiChatCompletionMessage("system", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionContext addUserMessage(String newMessageContent){
        OpenAiChatCompletionMessage newMessage = new OpenAiChatCompletionMessage("user", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public OpenAiChatCompletionContext addAssistantMessage(String newMessageContent){
        OpenAiChatCompletionMessage newMessage = new OpenAiChatCompletionMessage("assistant", newMessageContent);
        this.addMessage(newMessage);
        return this;
    }

    public List<OpenAiChatCompletionMessage> getContextMessages(){
        return this.contextMessages;
    }

    public OpenAiChatCompletionResponse createChatCompletion(){
        int requestContextVersion = ++this.contextVersion;

        requestBody.setMessages(this.contextMessages);
        System.out.println(requestBody.getMessages().toString());
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

                OpenAiChatCompletionMessage responseMessage = response.getObjectMessage();
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

    public static class Segment {

        private int size, token;
        
        public Segment(int size, int token){
            this.size = size;
            this.token = token;
        }
    
        public int getSize() {
            return size;
        }
    
        public void setSize(int size) {
            this.size = size;
        }
    
        public int getToken() {
            return token;
        }
    
        public void setToken(int token) {
            this.token = token;
        }
        
    }
}
