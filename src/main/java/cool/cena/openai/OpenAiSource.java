package cool.cena.openai;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;
import cool.cena.openai.context.OpenAiChatCompletionContext;

public class OpenAiSource {

    private OpenAiApiAccessor chatCompletionApiAccessor;

    private OpenAiChatCompletionProperties chatCompletionProperties;

    public OpenAiSource(String httpHeaderAuthorization, OpenAiChatCompletionProperties chatCompletionProperties){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        this.chatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);

        this.chatCompletionProperties = chatCompletionProperties;

    }

    public OpenAiSource(String httpHeaderAuthorization, String httpHeaderOpenAiOrganization){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        httpHeaders.set("OpenAI-Organization", httpHeaderOpenAiOrganization);
        
        this.chatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);
    }

    public OpenAiChatCompletionContext createChatCompletionContext(){
        return new OpenAiChatCompletionContext(this.chatCompletionApiAccessor, chatCompletionProperties);
    }
}
