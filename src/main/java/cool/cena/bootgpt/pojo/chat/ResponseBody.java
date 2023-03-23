package cool.cena.bootgpt.pojo.chat;

import java.util.List;

public class ResponseBody {

    private int status;
    private String id, object, model, errMessage;
    private Long created;
    private List<ResponseBodyChoice> choices;
    private ResponseBodyUsage usage;

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public ResponseBodyUsage getUsage() {
        return usage;
    }

    public void setUsage(ResponseBodyUsage usage) {
        this.usage = usage;
    }

    public List<ResponseBodyChoice> getChoices() {
        return choices;
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

    public boolean isNormal() {
        return status == 200;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Message getResponseMessage(){
        return this.choices.get(0).getMessage();
    }

}
