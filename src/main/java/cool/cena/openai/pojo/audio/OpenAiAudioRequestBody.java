package cool.cena.openai.pojo.audio;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiAudioProperties;

public class OpenAiAudioRequestBody {
    private Resource file;
    private String model;
    private String prompt;
    private String responseFormat;
    private Double temperature;

    public OpenAiAudioRequestBody(OpenAiAudioProperties imageProperties) {
        this.model = imageProperties.getModel();
        this.responseFormat = imageProperties.getResponseFormat();
        this.temperature = imageProperties.getTemperature();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Resource getFile() {
        return file;
    }

    public void setFile(Resource file) {
        this.file = file;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public MultiValueMap<String, Object> toMultiValueMap(){

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("file", this.file);
        multiValueMap.add("model", this.model);
        if(this.prompt != null){
            multiValueMap.add("prompt", this.prompt);
        }
        if (this.responseFormat != null) {
            multiValueMap.add("response_format", this.responseFormat);
        }
        if (this.temperature != null) {
            multiValueMap.add("temperature", this.temperature);
        }

        return multiValueMap;

    }
}
