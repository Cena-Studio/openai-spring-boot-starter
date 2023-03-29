package cool.cena.openai.pojo.image;

import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageProperties;

public class OpenAiImageRequestBody {
    private Integer n;

    @JsonProperty("response_format")
    private String responseFormat;
    private String size, user;

    public OpenAiImageRequestBody(OpenAiImageProperties imageProperties) {
        this.n = imageProperties.getN();
        this.size = imageProperties.getSize();
        this.responseFormat = imageProperties.getResponseFormat();
        this.user = imageProperties.getUser();
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
}
