package cool.cena.openai.pojo.image;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageGenerationProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiImageGenerationRequestBody {
        
    private Integer n;

    @JsonProperty("response_format")
    private String responseFormat;
    private String prompt, size, user;

    public OpenAiImageGenerationRequestBody(OpenAiImageGenerationProperties imageGenerationProperties) {
        this.n = imageGenerationProperties.getN();
        this.size = imageGenerationProperties.getSize();
        this.responseFormat = imageGenerationProperties.getResponseFormat();
        this.user = imageGenerationProperties.getUser();
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }


}
