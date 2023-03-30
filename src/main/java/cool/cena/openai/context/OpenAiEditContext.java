package cool.cena.openai.context;

import java.util.List;
import java.util.Map;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiEditProperties;
import cool.cena.openai.pojo.edit.OpenAiEditRequestBody;
import cool.cena.openai.pojo.edit.OpenAiEditResponseBody;

public class OpenAiEditContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiEditRequestBody requestBody;

    // constructor
    public OpenAiEditContext(OpenAiApiAccessor apiAccessor, OpenAiEditProperties openAiEditProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiEditRequestBody(openAiEditProperties);

    }

    // set request parameters
    public OpenAiEditContext setModel(String model) {
        this.requestBody.setModel(model);
        return this;
    }
    public OpenAiEditContext setTemperature(Double temperature) {
        this.requestBody.setTemperature(temperature);
        return this;
    }
    public OpenAiEditContext setTopP(Double topP) {
        this.requestBody.setTopP(topP);
        return this;
    }
    public OpenAiEditContext setN(Integer n) {
        this.requestBody.setN(n);
        return this;
    }

    public OpenAiEditResponseBody create(String instruction){
        return this.create("", instruction);
    }
    public OpenAiEditResponseBody create(String input, String instruction){
        requestBody.setInput(input);
        requestBody.setInstruction(instruction);
        return apiAccessor.sendRequest(requestBody);
    }
}
