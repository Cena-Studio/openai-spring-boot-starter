package cool.cena.openai;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import cool.cena.openai.chatcompletion.OpenAiChatCompletionContext;

public class OpenAiSource {

    private OpenAiApiAccessor openAiChatCompletionApiAccessor;

    public OpenAiSource(String httpHeaderAuthorization){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);

        this.openAiChatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);

    }

    public OpenAiSource(String httpHeaderAuthorization, String httpHeaderOpenAiOrganization){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        httpHeaders.set("OpenAI-Organization", httpHeaderOpenAiOrganization);
        
        this.openAiChatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);
    }

    public OpenAiChatCompletionContext newContext(){
        return new OpenAiChatCompletionContext(this.openAiChatCompletionApiAccessor);
    }
}
