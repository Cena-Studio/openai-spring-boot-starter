package cool.cena.openai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiChatCompletionProperties;
import cool.cena.openai.chatcompletion.OpenAiChatCompletionContext;
import cool.cena.openai.chatcompletion.pojo.OpenAiChatCompletionRequestBody;

public class OpenAiSource {

    private OpenAiApiAccessor openAiChatCompletionApiAccessor;

    private OpenAiChatCompletionProperties chatCompletionProperties;

    public OpenAiSource(String httpHeaderAuthorization, OpenAiChatCompletionProperties chatCompletionProperties){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        this.openAiChatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);

        this.chatCompletionProperties = chatCompletionProperties;

        System.out.println(this.chatCompletionProperties.getModel());
        System.out.println(this.chatCompletionProperties.getTemperature());
    }

    public OpenAiSource(String httpHeaderAuthorization, String httpHeaderOpenAiOrganization){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        httpHeaders.set("OpenAI-Organization", httpHeaderOpenAiOrganization);
        
        this.openAiChatCompletionApiAccessor = new OpenAiApiAccessor(httpHeaders);
    }

    public OpenAiChatCompletionContext createChatCompletionContext(){
        OpenAiChatCompletionRequestBody chatCompletionRequestBody = new OpenAiChatCompletionRequestBody(chatCompletionProperties);
        return new OpenAiChatCompletionContext(this.openAiChatCompletionApiAccessor, chatCompletionRequestBody);
    }
}
