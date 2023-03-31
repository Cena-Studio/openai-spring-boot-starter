package cool.cena.openai;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cool.cena.openai.exception.OpenAiUnknownException;
import cool.cena.openai.exception.audio.AudioResourceAccessException;
import cool.cena.openai.exception.audio.AudioStatusCodeException;
import cool.cena.openai.exception.OpenAiUnauthorizedException;
import cool.cena.openai.exception.chatcompletion.ChatCompletionBadRequestException;
import cool.cena.openai.exception.chatcompletion.ChatCompletionResourceAccessException;
import cool.cena.openai.exception.chatcompletion.ChatCompletionStatusCodeException;
import cool.cena.openai.exception.edit.EditResourceAccessException;
import cool.cena.openai.exception.edit.EditStatusCodeException;
import cool.cena.openai.exception.embedding.EmbeddingResourceAccessException;
import cool.cena.openai.exception.embedding.EmbeddingStatusCodeException;
import cool.cena.openai.exception.image.ImageBadRequestException;
import cool.cena.openai.exception.image.ImageResourceAccessException;
import cool.cena.openai.exception.image.ImageStatusCodeException;
import cool.cena.openai.exception.moderation.ModerationResourceAccessException;
import cool.cena.openai.exception.moderation.ModerationStatusCodeException;
import cool.cena.openai.exception.textcompletion.TextCompletionResourceAccessException;
import cool.cena.openai.exception.textcompletion.TextCompletionStatusCodeException;
import cool.cena.openai.pojo.audio.OpenAiAudioTranscriptionRequestBody;
import cool.cena.openai.pojo.audio.OpenAiAudioTranscriptionResponseBody;
import cool.cena.openai.pojo.audio.OpenAiAudioTranslationRequestBody;
import cool.cena.openai.pojo.audio.OpenAiAudioTranslationResponseBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionRequestBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponseBody;
import cool.cena.openai.pojo.edit.OpenAiEditRequestBody;
import cool.cena.openai.pojo.edit.OpenAiEditResponseBody;
import cool.cena.openai.pojo.embedding.OpenAiEmbeddingRequestBody;
import cool.cena.openai.pojo.embedding.OpenAiEmbeddingResponseBody;
import cool.cena.openai.pojo.image.OpenAiImageEditRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageEditResponseBody;
import cool.cena.openai.pojo.image.OpenAiImageGenerationRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageGenerationResponseBody;
import cool.cena.openai.pojo.image.OpenAiImageVariationRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageVariationResponseBody;
import cool.cena.openai.pojo.moderation.OpenAiModerationRequestBody;
import cool.cena.openai.pojo.moderation.OpenAiModerationResponseBody;
import cool.cena.openai.pojo.textcompletion.OpenAiTextCompletionRequestBody;
import cool.cena.openai.pojo.textcompletion.OpenAiTextCompletionResponseBody;

public class OpenAiApiAccessor {

    private final String TEXT_COMPLETION_URL = "https://api.openai.com/v1/completions";
    private final String CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";
    private final String EDIT_URL = "https://api.openai.com/v1/edits";
    private final String MODERATION_URL = "https://api.openai.com/v1/moderations";
    private final String IMAGE_GENERATION_URL = "https://api.openai.com/v1/images/generations";
    private final String IMAGE_EDIT_URL = "https://api.openai.com/v1/images/edits";
    private final String IMAGE_VARIATION_URL = "https://api.openai.com/v1/images/variations";
    private final String EMBEDDING_URL = "https://api.openai.com/v1/embeddings";
    private final String AUDIO_TRANSCRIPTION_URL = "https://api.openai.com/v1/audio/transcriptions";
    private final String AUDIO_TRANSLATION_URL = "https://api.openai.com/v1/audio/translations";


    private RestTemplate restTemplate;
    private HttpHeaders httpJsonHeaders, httpFileHeaders;

    public OpenAiApiAccessor(HttpHeaders httpHeaders){
        HttpHeaders httpJsonHeaders = new HttpHeaders();
        httpJsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpJsonHeaders.addAll(httpHeaders);
        this.httpJsonHeaders = httpJsonHeaders;
        
        HttpHeaders httpFileHeaders = new HttpHeaders();
        httpFileHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpFileHeaders.addAll(httpHeaders);
        this.httpFileHeaders = httpFileHeaders;

        this.restTemplate = new RestTemplate();
    }
    
    // text completion request
    public OpenAiTextCompletionResponseBody sendRequest(OpenAiTextCompletionRequestBody requestBody){

        HttpEntity<OpenAiTextCompletionRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiTextCompletionResponseBody responseBody = this.restTemplate.postForObject(this.TEXT_COMPLETION_URL, requestEntity, OpenAiTextCompletionResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new TextCompletionStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new TextCompletionResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }
    
    // chat completion request
    public OpenAiChatCompletionResponseBody sendRequest(OpenAiChatCompletionRequestBody requestBody){

        HttpEntity<OpenAiChatCompletionRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiChatCompletionResponseBody responseBody = this.restTemplate.postForObject(this.CHAT_COMPLETION_URL, requestEntity, OpenAiChatCompletionResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){

                throw new ChatCompletionBadRequestException(e.getMessage());

            }else if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ChatCompletionStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ChatCompletionResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // edit request
    public OpenAiEditResponseBody sendRequest(OpenAiEditRequestBody requestBody){

        HttpEntity<OpenAiEditRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiEditResponseBody responseBody = this.restTemplate.postForObject(this.EDIT_URL, requestEntity, OpenAiEditResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new EditStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new EditResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // moderation request
    public OpenAiModerationResponseBody sendRequest(OpenAiModerationRequestBody requestBody){

        HttpEntity<OpenAiModerationRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiModerationResponseBody responseBody = this.restTemplate.postForObject(this.MODERATION_URL, requestEntity, OpenAiModerationResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ModerationStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ModerationResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // image generation request
    public OpenAiImageGenerationResponseBody sendRequest(OpenAiImageGenerationRequestBody requestBody){

        HttpEntity<OpenAiImageGenerationRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiImageGenerationResponseBody responseBody = this.restTemplate.postForObject(this.IMAGE_GENERATION_URL, requestEntity, OpenAiImageGenerationResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ImageStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ImageResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // image edit request
    public OpenAiImageEditResponseBody sendRequest(OpenAiImageEditRequestBody requestBody){

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody.toMultiValueMap(), httpFileHeaders);
        
        try{

            OpenAiImageEditResponseBody responseBody = this.restTemplate.postForObject(this.IMAGE_EDIT_URL, requestEntity, OpenAiImageEditResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){

                throw new ImageBadRequestException(e.getMessage());

            }else if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ImageStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ImageResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }
    


    // image variation request
    public OpenAiImageVariationResponseBody sendRequest(OpenAiImageVariationRequestBody requestBody){

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody.toMultiValueMap(), httpFileHeaders);
        
        try{

            OpenAiImageVariationResponseBody responseBody = this.restTemplate.postForObject(this.IMAGE_VARIATION_URL, requestEntity, OpenAiImageVariationResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){

                throw new ImageBadRequestException(e.getMessage());

            }else if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ImageStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ImageResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // embedding request
    public OpenAiEmbeddingResponseBody sendRequest(OpenAiEmbeddingRequestBody requestBody){

        HttpEntity<OpenAiEmbeddingRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiEmbeddingResponseBody responseBody = this.restTemplate.postForObject(this.EMBEDDING_URL, requestEntity, OpenAiEmbeddingResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new EmbeddingStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new EmbeddingResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // audio transcription request
    public <T> T sendRequest(OpenAiAudioTranscriptionRequestBody requestBody, Class<T> responseClass){

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody.toMultiValueMap(), httpFileHeaders);
        
        try{

            T responseBody = this.restTemplate.postForObject(this.AUDIO_TRANSCRIPTION_URL, requestEntity, responseClass);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new AudioStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new AudioResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // audio translation request
    public <T> T sendRequest(OpenAiAudioTranslationRequestBody requestBody, Class<T> responseClass){

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody.toMultiValueMap(), httpFileHeaders);
        
        try{

            T responseBody = this.restTemplate.postForObject(this.AUDIO_TRANSLATION_URL, requestEntity, responseClass);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new AudioStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new AudioResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }
}