package cool.cena.openai.pojo.chatcompletion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiChatCompletionResponseBody {

    private String id, object, model;
    private Long created;
    private List<OpenAiChatCompletionResponseChoice> choices;
    private OpenAiChatCompletionResponseUsage usage;

    // getters and setters
    public String getId(){
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject(){
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getModel(){
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCreated(){
        return this.created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public OpenAiChatCompletionResponseUsage getUsage(){
        return this.usage;
    }

    public void setUsage(OpenAiChatCompletionResponseUsage usage) {
        this.usage = usage;
    }
    
    public List<OpenAiChatCompletionResponseChoice> getChoices(){
        return this.choices;
    }

    public void setChoices(List<OpenAiChatCompletionResponseChoice> choices) {
        this.choices = choices;
    }

    public int getPromptToken(){
        return this.usage.getPromptTokens();
    }

    public int getCompletionToken(){
        return this.usage.getCompletionTokens();
    }

    public ChatCompletionMessage getObjectMessage(){
        return this.getObjectMessage(0);
    }

    public ChatCompletionMessage getObjectMessage(int choice){
        return this.choices.get(choice).getMessage();
    }

    public String getMessage(){
        return this.getMessage(0);
    }

    public String getMessage(int choice){
        return this.choices.get(choice).getMessage().getContent();
    }

    public static class OpenAiChatCompletionResponseChoice {
    
        private int index;
        private ChatCompletionMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
        
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public ChatCompletionMessage getMessage() {
            return message;
        }
        public void setMessage(ChatCompletionMessage message) {
            this.message = message;
        }
        public String getFinishReason() {
            return finishReason;
        }
        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
        
    }

    public static class OpenAiChatCompletionResponseUsage {
    
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;
    
        // getters and setters
        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getCompletionTokens() {
            return completionTokens;
        }

        public void setCompletionTokens(int completionTokens) {
            this.completionTokens = completionTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }
    }
}
