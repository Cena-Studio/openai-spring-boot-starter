package cool.cena.openai;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cool.cena.openai.exception.OpenAiChatCompletionBadRequestException;
import cool.cena.openai.exception.OpenAiChatCompletionStatusCodeException;
import cool.cena.openai.exception.OpenAiChatCompletionUnauthorizedException;
import cool.cena.openai.exception.OpenAiChatCompletionUnknownException;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionRequestBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponse;

public class OpenAiApiAccessor {

    private final String CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public OpenAiApiAccessor(HttpHeaders httpHeaders){
        this.restTemplate = new RestTemplate();
        this.httpHeaders = httpHeaders;
    }
    
    public OpenAiChatCompletionResponse sendRequest(OpenAiChatCompletionRequestBody requestBody){

        HttpEntity<OpenAiChatCompletionRequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        try{

            OpenAiChatCompletionResponse responseBody = this.restTemplate.postForObject(this.CHAT_COMPLETION_URL, requestEntity, OpenAiChatCompletionResponse.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){

                throw new OpenAiChatCompletionBadRequestException();

            }else if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiChatCompletionUnauthorizedException();

            }else{

                throw new OpenAiChatCompletionStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(RestClientException e){

            throw new OpenAiChatCompletionUnknownException(e.getMessage());

        }
    }
    
}