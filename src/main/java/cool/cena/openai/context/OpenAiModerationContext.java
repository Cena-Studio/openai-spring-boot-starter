package cool.cena.openai.context;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiModerationProperties;
import cool.cena.openai.pojo.moderation.OpenAiModerationRequestBody;
import cool.cena.openai.pojo.moderation.OpenAiModerationResponseBody;

public class OpenAiModerationContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiModerationRequestBody requestBody;

    // constructor
    public OpenAiModerationContext(OpenAiApiAccessor apiAccessor, OpenAiModerationProperties openAiModerationProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiModerationRequestBody(openAiModerationProperties);

    }

    // set request parameters
    public OpenAiModerationContext setModel(String model) {
        this.requestBody.setModel(model);
        return this;
    }

    // make a request
    public OpenAiModerationResponseBody create(String input){
        this.requestBody.setInput(input);
        return apiAccessor.sendRequest(requestBody);
    }
}
