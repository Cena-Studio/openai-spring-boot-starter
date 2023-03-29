package cool.cena.openai.context;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageGenerationProperties;
import cool.cena.openai.pojo.image.OpenAiImageGenerationRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageGenerationResponseBody;
public class OpenAiImageGenerationContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiImageGenerationRequestBody requestBody;

    // constructor
    public OpenAiImageGenerationContext(OpenAiApiAccessor apiAccessor, OpenAiImageGenerationProperties openAiImageGenerationProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiImageGenerationRequestBody(openAiImageGenerationProperties);

    }

    // set request parameters
    public OpenAiImageGenerationContext setN(Integer n) {
        this.requestBody.setN(n);
        return this;
    }
    public OpenAiImageGenerationContext setSize(String size) {
        this.requestBody.setSize(size);
        return this;
    }
    public OpenAiImageGenerationContext setResponseFormat(String responseFormat) {
        this.requestBody.setResponseFormat(responseFormat);
        return this;
    }
    public OpenAiImageGenerationContext setUser(String user) {
        this.requestBody.setUser(user);
        return this;
    }
    

    // make a request
    public OpenAiImageGenerationResponseBody create(String prompt){
        this.requestBody.setPrompt(prompt);
        return apiAccessor.sendRequest(requestBody);
    }
}
