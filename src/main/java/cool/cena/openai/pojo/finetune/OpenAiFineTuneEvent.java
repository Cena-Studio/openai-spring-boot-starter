package cool.cena.openai.pojo.finetune;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiFineTuneEvent {
    private String object, level, message;
    @JsonProperty("created_at")
    private Long createdAt;

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    
}
