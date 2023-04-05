package cool.cena.openai.pojo.finetune;

import java.util.List;

public class OpenAiListResponseBody <T> {
    String object;
    List<T> data;

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

}
