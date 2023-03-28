package cool.cena.openai.pojo.textcompletion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiTextCompletionResponseBody {

    private String id, object, model;
    private Long created;
    private List<OpenAiTextCompletionResponseChoice> choices;
    private OpenAiTextCompletionResponseUsage usage;

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

    public OpenAiTextCompletionResponseUsage getUsage(){
        return this.usage;
    }

    public void setUsage(OpenAiTextCompletionResponseUsage usage) {
        this.usage = usage;
    }
    
    public List<OpenAiTextCompletionResponseChoice> getChoices(){
        return this.choices;
    }

    public void setChoices(List<OpenAiTextCompletionResponseChoice> choices) {
        this.choices = choices;
    }

    public int getPromptToken(){
        return this.usage.getPromptTokens();
    }

    public int getCompletionToken(){
        return this.usage.getCompletionTokens();
    }

    public String getText(){
        return this.getText(0);
    }

    public String getText(int choice){
        return this.choices.get(choice).getText();
    }

    public static class OpenAiTextCompletionResponseChoice {
    
        private int index;
        private String text, prompt;
        private Integer logprobs;
        @JsonProperty("finish_reason")
        private String finishReason;
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public Integer getLogprobs() {
            return logprobs;
        }
        public void setLogprobs(Integer logprobs) {
            this.logprobs = logprobs;
        }
        public String getFinishReason() {
            return finishReason;
        }
        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
        public String getPrompt() {
            return prompt;
        }
        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
        
        
        
    }

    public static class OpenAiTextCompletionResponseUsage {
    
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
