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
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponseBody;
import cool.cena.openai.pojo.image.OpenAiImageGenerationRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageGenerationResponseBody;
import cool.cena.openai.pojo.moderation.OpenAiModerationRequestBody;
import cool.cena.openai.pojo.moderation.OpenAiModerationResponseBody;
import cool.cena.openai.pojo.textcompletion.OpenAiTextCompletionRequestBody;
import cool.cena.openai.pojo.textcompletion.OpenAiTextCompletionResponseBody;

public class OpenAiApiAccessor {

    private final String TEXT_COMPLETION_URL = "https://api.openai.com/v1/completions";
    private final String CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";
    private final String MODERATION_URL = "https://api.openai.com/v1/moderations";
    private final String IMAGE_GENERATION_URL = "https://api.openai.com/v1/images/generations";


    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public OpenAiApiAccessor(HttpHeaders httpHeaders){
        this.restTemplate = new RestTemplate();
        this.httpHeaders = httpHeaders;
    }
    
    // text completion request
    public OpenAiTextCompletionResponseBody sendRequest(OpenAiTextCompletionRequestBody requestBody){

        HttpEntity<OpenAiTextCompletionRequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        try{

            OpenAiTextCompletionResponseBody responseBody = this.restTemplate.postForObject(this.TEXT_COMPLETION_URL, requestEntity, OpenAiTextCompletionResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){
                System.out.println(e.getMessage());

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
    
    // chat completion request
    public OpenAiChatCompletionResponseBody sendRequest(OpenAiChatCompletionRequestBody requestBody){

        HttpEntity<OpenAiChatCompletionRequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        try{

            OpenAiChatCompletionResponseBody responseBody = this.restTemplate.postForObject(this.CHAT_COMPLETION_URL, requestEntity, OpenAiChatCompletionResponseBody.class);
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

    // moderation request
    public OpenAiModerationResponseBody sendRequest(OpenAiModerationRequestBody requestBody){

        HttpEntity<OpenAiModerationRequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        try{

            OpenAiModerationResponseBody responseBody = this.restTemplate.postForObject(this.MODERATION_URL, requestEntity, OpenAiModerationResponseBody.class);
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

    // image generation request
    public OpenAiImageGenerationResponseBody sendRequest(OpenAiImageGenerationRequestBody requestBody){

        HttpEntity<OpenAiImageGenerationRequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        try{

            OpenAiImageGenerationResponseBody responseBody = this.restTemplate.postForObject(this.IMAGE_GENERATION_URL, requestEntity, OpenAiImageGenerationResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){
                System.out.println(e.getMessage());

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