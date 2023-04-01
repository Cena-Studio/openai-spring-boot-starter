package cool.cena.openai.context;

import java.util.List;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiEmbeddingProperties;
import cool.cena.openai.pojo.embedding.OpenAiEmbeddingRequestBody;
import cool.cena.openai.pojo.embedding.OpenAiEmbeddingResponseBody;

public class OpenAiEmbeddingContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiEmbeddingRequestBody requestBody;

    // constructor
    public OpenAiEmbeddingContext(OpenAiApiAccessor apiAccessor, OpenAiEmbeddingProperties openAiEmbeddingProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiEmbeddingRequestBody(openAiEmbeddingProperties);

    }

    // set request parameters
    public OpenAiEmbeddingContext setModel(String model) {
        this.requestBody.setModel(model);
        return this;
    }
    public OpenAiEmbeddingContext setUser(String user) {
        this.requestBody.setUser(user);
        return this;
    }

    public OpenAiEmbeddingResponseBody create(String input){
        requestBody.setInput(input);
        return apiAccessor.sendRequest(requestBody);
    }

    public OpenAiEmbeddingResponseBody create(List<Integer> input){
        requestBody.setInput(input);
        return apiAccessor.sendRequest(requestBody);
    }
}
