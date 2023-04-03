package cool.cena.openai.pojo.file;

import java.util.List;

public class OpenAiListFileResponseBody {

    private String object;
    private List<OpenAiFile> data;

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public List<OpenAiFile> getData() {
        return data;
    }
    public void setData(List<OpenAiFile> data) {
        this.data = data;
    }
    
}
