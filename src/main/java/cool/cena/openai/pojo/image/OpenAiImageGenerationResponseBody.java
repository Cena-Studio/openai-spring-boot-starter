package cool.cena.openai.pojo.image;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiImageGenerationResponseBody {

    private String created;
    private List<Map<String, String>> data;
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public List<Map<String, String>> getData() {
        return data;
    }
    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
    
}
