package cool.cena.openai.pojo.file;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class OpenAiFileRequestBody {

    private Resource file;
    private String purpose;

    public Resource getFile() {
        return file;
    }

    public void setFile(Resource file) {
        this.file = file;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public MultiValueMap<String, Object> toMultiValueMap(){

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        
        multiValueMap.add("file", file);
        multiValueMap.add("purpose", purpose);

        return multiValueMap;

    }

}
    
