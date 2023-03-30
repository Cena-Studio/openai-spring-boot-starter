package cool.cena.openai.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;
import cool.cena.openai.exception.chatcompletion.ChatCompletionOutDatedException;
import cool.cena.openai.pojo.chatcompletion.ChatCompletionMessage;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionRequestBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponseBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponseBody.OpenAiChatCompletionResponseChoice;

public class OpenAiChatCompletionContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiChatCompletionRequestBody requestBody;
    private MessageSearchTree messageSearchTree;
    private Version version;
    private Integer maxPromptToken;

    // constructor
    public OpenAiChatCompletionContext(OpenAiApiAccessor apiAccessor, OpenAiChatCompletionProperties openAiChatCompletionProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiChatCompletionRequestBody(openAiChatCompletionProperties);
        this.maxPromptToken = openAiChatCompletionProperties.getMaxPromptToken();
        this.messageSearchTree = new MessageSearchTree();
        this.version = new Version(new ArrayList<>());

    }



    // set request parameters
    public OpenAiChatCompletionContext setModel(String model) {
        this.requestBody.setModel(model);
        return this;
    }

    public OpenAiChatCompletionContext setUser(String user) {
        this.requestBody.setUser(user);
        return this;
    }
    public OpenAiChatCompletionContext setTemperature(Double temperature) {
        this.requestBody.setTemperature(temperature);
        return this;
    }
    public OpenAiChatCompletionContext setTopP(Double topP) {
        this.requestBody.setTopP(topP);
        return this;
    }
    public OpenAiChatCompletionContext setMaxPromptToken(Integer maxPromptToken) {
        this.maxPromptToken = maxPromptToken;
        return this;
    }
    public OpenAiChatCompletionContext setMaxCompletionToken(Integer maxCompletionToken) {
        this.requestBody.setMaxCompletionToken(maxCompletionToken);
        return this;
    }
    public OpenAiChatCompletionContext setN(Integer n) {
        this.requestBody.setN(n);
        return this;
    }
    public OpenAiChatCompletionContext setPresencePenalty(Double presencePenalty) {
        this.requestBody.setPresencePenalty(presencePenalty);
        return this;
    }
    public OpenAiChatCompletionContext setFrequencyPenalty(Double frequencyPenalty) {
        this.requestBody.setFrequencyPenalty(frequencyPenalty);
        return this;
    }
    public OpenAiChatCompletionContext setLogitBias(Map<Integer, Double> logitBias) {
        this.requestBody.setLogitBias(logitBias);
        return this;
    }
    public OpenAiChatCompletionContext setStop(List<String> stop) {
        this.requestBody.setStop(stop);
        return this;
    }



    // insert message
    private void addMessage(ChatCompletionMessage newMessage){
        Version newVersion = messageSearchTree.insert(this.version, newMessage);
        this.version = newVersion;
        messageSearchTree.print(this.version);
    }

    private void addMessage(ChatCompletionMessage newMessage, int token){
        Version newVersion = messageSearchTree.insert(this.version, newMessage, token);
        this.version = newVersion;
        messageSearchTree.print(this.version);
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



    // version control
    public Version getVersion(){
        return this.version;
    }

    public OpenAiChatCompletionContext switchVersion(int n){
        this.version = this.version.translate(n);
        messageSearchTree.refresh(this.version);
        messageSearchTree.print(this.version);
        return this;
    }

    public OpenAiChatCompletionContext switchVersion(Version version){
        this.version = version;
        messageSearchTree.refresh(this.version);
        return this;
    }



    // make requests
    public OpenAiChatCompletionResponseBody create(){

        Version requestVersion = new Version(this.version);

        PromptMessage promptMessage = messageSearchTree.getPromptMessage(this.version, this.maxPromptToken);
        int requestPromptToken = promptMessage.promptToken;
        requestBody.setMessages(promptMessage.promptMessages);
        OpenAiChatCompletionResponseBody response = this.apiAccessor.sendRequest(requestBody);

        // the following lines execute after the response from opanAiApiAccessor received
        // current context is the latest context
        if(messageSearchTree.checkLatestVersion(requestVersion)){

            // token process
            int responsePromptToken = response.getPromptToken();
            int newPromptToken = responsePromptToken - requestPromptToken;
            messageSearchTree.setToken(requestVersion, newPromptToken);

            List<OpenAiChatCompletionResponseChoice> responseChoices = response.getChoices();
            
            // only one choice
            if(responseChoices.size() == 1){
                int responseCompletionToken = response.getCompletionToken();
                ChatCompletionMessage responseMessage = response.getObjectMessage();
                this.addMessage(responseMessage,responseCompletionToken);

            // more than one choice
            }else{
                // insert the first choice into the message search tree and the version
                this.addMessage(response.getObjectMessage());
                // insert other choices into the message search tree
                for(int i = 1; i < responseChoices.size(); i++){
                    messageSearchTree.insert(requestVersion, response.getObjectMessage(i));
                }
            }
    
            return response;

        }

        // context has been updated during the request
        throw new ChatCompletionOutDatedException();
        
    }

    public OpenAiChatCompletionResponseBody create(String newMessageContent){
        return this.addUserMessage(newMessageContent).create();
    }



    // inner classes
    public static class Version{  
    
        private List<Integer> path;

        private Version(List<Integer> path) {
            this.path = path;
        }

        // construct a version instance by copying an existing version
        private Version(Version version){
            this.path = new ArrayList<>();
            for(Integer location: version.path){
                this.path.add(location);
            }
        }

        private Version translate(int n){
            List<Integer> newPath = new ArrayList<>();
            for(int i = 0; i < this.path.size() - 1; i ++){
                newPath.add(this.path.get(i));
            }
            newPath.add(n);
            return new Version(newPath);
        }

        public String toString(){
            String returnString = "context version: ";
            for(Integer location : path){
                returnString += location + "-";
            }
            return returnString;
        }
    }

    private static class PromptMessage{
    
        private List<ChatCompletionMessage> promptMessages;
        private int promptToken;

        private PromptMessage(List<ChatCompletionMessage> promptMessages, int promptToken) {
            this.promptMessages = promptMessages;
            this.promptToken = promptToken;
        }

    }

    private static class MessageSearchTree {
    
        private List<MessageNode> root;
    
        private MessageSearchTree(){
            this.root = new ArrayList<>();
        }
    
        private Version insert(Version version, ChatCompletionMessage message){
    
            return this.insert(version, message, 0);
    
        }
        
        private Version insert(Version version, ChatCompletionMessage message, int token){
    
            MessageNode pathNode;
            List<MessageNode> pathChilds = root;
            for (int i = 0; i < version.path.size(); i++) {
                pathNode = pathChilds.get(version.path.get(i));
                pathChilds = pathNode.childs;
            }
    
            List<Integer> newPath = new ArrayList<>();
            newPath.addAll(version.path);
            newPath.add(pathChilds.size());
    
            MessageNode newNode = new MessageNode(message, token);
            pathChilds.add(newNode);
    
            return new Version(newPath);
    
        }
    
        private PromptMessage getPromptMessage(Version version, int maxToken){
    
            MessageNode pathNode;
            List<MessageNode> pathChilds = root;
            List<MessageNode> promptNodes = new ArrayList<>();
    
            // iteratively add the nodes on the path into a template arraylist and control the token consumption
            int cumulativeToken = 0;
            for (int i = 0; i < version.path.size(); i++) {
                // add the current path node into the template arraylist
                pathNode = pathChilds.get(version.path.get(i));
                promptNodes.add(pathNode);
    
                // control the token consumption of the prompt to meet the maximum token limit
                cumulativeToken += pathNode.token;
                while(cumulativeToken > maxToken){
                    int reducedToken = promptNodes.remove(0).token;
                    cumulativeToken -= reducedToken;
                }
                
                // get children of the current path node
                pathChilds = pathNode.childs;
    
            }

            // form the promptNodes to promptMessages
            List<ChatCompletionMessage> promptMessages = new ArrayList<>();
            for(MessageNode promptNode : promptNodes){
                promptMessages.add(promptNode.message);
            }
    
            return new PromptMessage(promptMessages, cumulativeToken);
    
        }

        private boolean checkLatestVersion(Version version){
            MessageNode pathNode;
            List<MessageNode> pathChilds = root;
            for (int i = 0; i < version.path.size(); i++) {
                pathNode = pathChilds.get(version.path.get(i));
                pathChilds = pathNode.childs;
            }

            return pathChilds.size() == 0;
        }

        private void setToken(Version version, int token){
            
            MessageNode pathNode = null;
            List<MessageNode> pathChilds = root;
            for (int i = 0; i < version.path.size(); i++) {
                pathNode = pathChilds.get(version.path.get(i));
                pathChilds = pathNode.childs;
            }
            pathNode.token = token;
        }

        private void refresh(Version version){
            MessageNode pathNode = null;
            List<MessageNode> pathChilds = root;
            for (int i = 0; i < version.path.size(); i++) {
                pathNode = pathChilds.get(version.path.get(i));
                pathChilds = pathNode.childs;
            }
            pathNode.childs = new ArrayList<>();
        }

        private void print(Version version){
            
            MessageNode pathNode = null;
            List<MessageNode> pathChilds = root;
            for (int i = 0; i < version.path.size(); i++) {
                pathNode = pathChilds.get(version.path.get(i));
                pathChilds = pathNode.childs;
            }
        }
    
        private static class MessageNode{
    
            private List<MessageNode> childs;
            private ChatCompletionMessage message;
            private int token;
        
            private MessageNode(ChatCompletionMessage message, int token) {
                this.childs = new ArrayList<>();
                this.message = message;
                this.token = token;
            }
    
        }
        
    }
}
