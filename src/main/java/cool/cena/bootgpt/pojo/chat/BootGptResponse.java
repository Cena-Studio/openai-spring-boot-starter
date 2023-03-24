package cool.cena.bootgpt.pojo.chat;

import java.util.List;

public class BootGptResponse {

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

    public Message getObjectMessage(){
        return this.choices.get(0).getMessage();
    }

    public String getMessage(){
        if (this.status == 200) {
            return this.choices.get(0).getMessage().getContent();
        }
        return null;
    }

}
