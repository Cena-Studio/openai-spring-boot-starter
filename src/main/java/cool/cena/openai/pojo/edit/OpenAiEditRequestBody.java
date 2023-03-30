package cool.cena.openai.pojo.edit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiEditProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiEditRequestBody {
    private String model, input, instruction;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    private Integer n;

    public OpenAiEditRequestBody(OpenAiEditProperties editProperties) {
        this.model = editProperties.getModel();
        this.temperature = editProperties.getTemperature();
        this.topP = editProperties.getTopP();
        this.n = editProperties.getN();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }



}
