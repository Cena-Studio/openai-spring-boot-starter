package cool.cena.openai.context;

import java.util.List;
import java.util.Map;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiTextCompletionProperties;
import cool.cena.openai.pojo.textcompletion.OpenAiTextCompletionRequestBody;
import cool.cena.openai.pojo.textcompletion.OpenAiTextCompletionResponseBody;

public class OpenAiTextCompletionContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiTextCompletionRequestBody requestBody;

    // constructor
    public OpenAiTextCompletionContext(OpenAiApiAccessor apiAccessor, OpenAiTextCompletionProperties openAiTextCompletionProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiTextCompletionRequestBody(openAiTextCompletionProperties);

    }

    // set request parameters
    public OpenAiTextCompletionContext setModel(String model) {
        this.requestBody.setModel(model);
        return this;
    }

    public OpenAiTextCompletionContext setSuffix(String suffix) {
        this.requestBody.setSuffix(suffix);
        return this;
    }

    public OpenAiTextCompletionContext setUser(String user) {
        this.requestBody.setUser(user);
        return this;
    }
    public OpenAiTextCompletionContext setTemperature(Double temperature) {
        this.requestBody.setTemperature(temperature);
        return this;
    }
    public OpenAiTextCompletionContext setTopP(Double topP) {
        this.requestBody.setTopP(topP);
        return this;
    }
    public OpenAiTextCompletionContext setMaxTokens(Integer maxTokens) {
        this.requestBody.setMaxTokens(maxTokens);
        return this;
    }
    public OpenAiTextCompletionContext setN(Integer n) {
        this.requestBody.setN(n);
        return this;
    }
    public OpenAiTextCompletionContext setEcho(boolean echo) {
        this.requestBody.setEcho(echo);
        return this;
    }
    public OpenAiTextCompletionContext setPresencePenalty(Double presencePenalty) {
        this.requestBody.setPresencePenalty(presencePenalty);
        return this;
    }
    public OpenAiTextCompletionContext setFrequencyPenalty(Double frequencyPenalty) {
        this.requestBody.setFrequencyPenalty(frequencyPenalty);
        return this;
    }
    public OpenAiTextCompletionContext setLogitBias(Map<Integer, Double> logitBias) {
        this.requestBody.setLogitBias(logitBias);
        return this;
    }
    public OpenAiTextCompletionContext setStop(List<String> stop) {
        this.requestBody.setStop(stop);
        return this;
    }

    public OpenAiTextCompletionResponseBody create(String promptText){
        requestBody.setPrompt(promptText);
        return apiAccessor.sendRequest(requestBody);
    }
}
