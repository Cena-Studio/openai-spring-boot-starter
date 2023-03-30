package cool.cena.openai;

import org.springframework.http.HttpHeaders;

import cool.cena.openai.autoconfigure.OpenAiProperties;
import cool.cena.openai.context.OpenAiChatCompletionContext;
import cool.cena.openai.context.OpenAiImageEditContext;
import cool.cena.openai.context.OpenAiImageGenerationContext;
import cool.cena.openai.context.OpenAiImageVariationContext;
import cool.cena.openai.context.OpenAiModerationContext;
import cool.cena.openai.context.OpenAiTextCompletionContext;

public class OpenAiSource {

    private OpenAiApiAccessor openAiApiAccessor;

    private OpenAiProperties openAiProperties;

    public OpenAiSource(String httpHeaderAuthorization, OpenAiProperties openAiProperties){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        this.openAiApiAccessor = new OpenAiApiAccessor(httpHeaders);

        this.openAiProperties = openAiProperties;

    }

    public OpenAiSource(String httpHeaderAuthorization, String httpHeaderOpenAiOrganization, OpenAiProperties openAiProperties){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        httpHeaders.set("OpenAI-Organization", httpHeaderOpenAiOrganization);
        this.openAiApiAccessor = new OpenAiApiAccessor(httpHeaders);

        this.openAiProperties = openAiProperties;
    }

    public OpenAiTextCompletionContext createTextCompletionContext(){
        return new OpenAiTextCompletionContext(this.openAiApiAccessor, openAiProperties.getTextCompletion());
    }

    public OpenAiChatCompletionContext createChatCompletionContext(){
        return new OpenAiChatCompletionContext(this.openAiApiAccessor, openAiProperties.getChatCompletion());
    }

    public OpenAiModerationContext createModerationContext(){
        return new OpenAiModerationContext(this.openAiApiAccessor, openAiProperties.getModeration());
    }

    public OpenAiImageGenerationContext createImageGenerationContext(){
        return new OpenAiImageGenerationContext(this.openAiApiAccessor, openAiProperties.getImageGeneration());
    }

    public OpenAiImageEditContext createImageEditContext(){
        return new OpenAiImageEditContext(this.openAiApiAccessor, openAiProperties.getImageEdit());
    }

    public OpenAiImageVariationContext createImageVariationContext(){
        return new OpenAiImageVariationContext(this.openAiApiAccessor, openAiProperties.getImageVariation());
    }
}
