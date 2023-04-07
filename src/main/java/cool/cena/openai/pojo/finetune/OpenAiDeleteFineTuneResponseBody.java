package cool.cena.openai.pojo.finetune;

public class OpenAiDeleteFineTuneResponseBody {
    
    private String id, object;
    private boolean deleted;

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
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
