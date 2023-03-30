package cool.cena.openai.pojo.edit;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiEditResponseBody {

    private String object;
    private Long created;
    private List<OpenAiEditResponseChoice> choices;
    private OpenAiEditResponseUsage usage;

    // getters and setters
    public String getObject(){
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated(){
        return this.created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public OpenAiEditResponseUsage getUsage(){
        return this.usage;
    }

    public void setUsage(OpenAiEditResponseUsage usage) {
        this.usage = usage;
    }
    
    public List<OpenAiEditResponseChoice> getChoices(){
        return this.choices;
    }

    public void setChoices(List<OpenAiEditResponseChoice> choices) {
        this.choices = choices;
    }

    public int getPromptTokens(){
        return this.usage.getPromptTokens();
    }

    public int getCompletionTokens(){
        return this.usage.getCompletionTokens();
    }

    public String getText(){
        return this.getText(0);
    }

    public String getText(int choice){
        return this.choices.get(choice).getText();
    }

    public static class OpenAiEditResponseChoice {
    
        private int index;
        private String text;

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
        
    }

    public static class OpenAiEditResponseUsage {
    
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
