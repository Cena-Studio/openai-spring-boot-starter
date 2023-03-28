package cool.cena.openai;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import cool.cena.openai.autoconfigure.OpenAiProperties;
import cool.cena.openai.context.OpenAiChatCompletionContext;
import cool.cena.openai.context.OpenAiTextCompletionContext;

public class OpenAiSource {

    private OpenAiApiAccessor chatCompletionApiAccessor;

    private OpenAiProperties openAiProperties;

    public OpenAiSource(String httpHeaderAuthorization, OpenAiProperties openAiProperties){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        this.chatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);

        this.openAiProperties = openAiProperties;

    }

    public OpenAiSource(String httpHeaderAuthorization, String httpHeaderOpenAiOrganization, OpenAiProperties openAiProperties){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        httpHeaders.set("OpenAI-Organization", httpHeaderOpenAiOrganization);
        this.chatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);

        this.openAiProperties = openAiProperties;
    }

    public OpenAiTextCompletionContext createTextCompletionContext(){
        return new OpenAiTextCompletionContext(this.chatCompletionApiAccessor, openAiProperties.getTextCompletion());
    }

    public OpenAiChatCompletionContext createChatCompletionContext(){
        return new OpenAiChatCompletionContext(this.chatCompletionApiAccessor, openAiProperties.getChatCompletion());
    }
}
