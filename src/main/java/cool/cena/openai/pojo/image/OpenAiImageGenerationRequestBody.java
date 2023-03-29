package cool.cena.openai.pojo.image;

import com.fasterxml.jackson.annotation.JsonInclude;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageGenerationProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiImageGenerationRequestBody extends OpenAiImageRequestBody {

    private String prompt;

    public OpenAiImageGenerationRequestBody(OpenAiImageGenerationProperties imageGenerationProperties) {
        super(imageGenerationProperties);
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }


}
