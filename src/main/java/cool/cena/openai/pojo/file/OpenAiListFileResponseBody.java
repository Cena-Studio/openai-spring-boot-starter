package cool.cena.openai.pojo.file;

import java.util.List;

public class OpenAiListFileResponseBody {

    private String object;
    private List<OpenAiFileResponseBody> data;

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public List<OpenAiFileResponseBody> getData() {
        return data;
    }
    public void setData(List<OpenAiFileResponseBody> data) {
        this.data = data;
    }
    
}
