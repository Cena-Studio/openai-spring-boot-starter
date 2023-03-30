package cool.cena.openai.pojo.embedding;

import com.fasterxml.jackson.annotation.JsonInclude;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiEmbeddingProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiEmbeddingRequestBody {
    private String input, model, user;

    public OpenAiEmbeddingRequestBody(OpenAiEmbeddingProperties embeddingProperties) {
        this.model = embeddingProperties.getModel();
        this.user =   embeddingProperties.getUser();
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
