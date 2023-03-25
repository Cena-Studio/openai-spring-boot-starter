package cool.cena.openai.chatcompletion.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiChatCompletionResponse {

    private int status;
    private String id, object, model, errMessage;
    private Long created;
    private List<ResponseBodyChoice> choices;
    private ResponseBodyUsage usage;

    // getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public void setUsage(ResponseBodyUsage usage) {
        this.usage = usage;
    }
    
    public void setChoices(List<ResponseBodyChoice> choices) {
        this.choices = choices;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPromptToken(){
        return this.usage.getPromptTokens();
    }

    public int getCompletionToken(){
        return this.usage.getCompletionTokens();
    }

    public OpenAiChatCompletionMessage getObjectMessage(){
        return this.choices.get(0).getMessage();
    }

    public String getMessage(){
        if (this.status == 200) {
            return this.choices.get(0).getMessage().getContent();
        }
        return null;
    }

    public static class ResponseBodyChoice {
    
        private int index;
        private OpenAiChatCompletionMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
        
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public OpenAiChatCompletionMessage getMessage() {
            return message;
        }
        public void setMessage(OpenAiChatCompletionMessage message) {
            this.message = message;
        }
        public String getFinishReason() {
            return finishReason;
        }
        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
        
    }

    public static class ResponseBodyUsage {
    
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
