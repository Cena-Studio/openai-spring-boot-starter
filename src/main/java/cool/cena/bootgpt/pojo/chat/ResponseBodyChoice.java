package cool.cena.bootgpt.pojo.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBodyChoice {
    
    private int index;
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;
    
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public String getFinishReason() {
        return finishReason;
    }
    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    
}
