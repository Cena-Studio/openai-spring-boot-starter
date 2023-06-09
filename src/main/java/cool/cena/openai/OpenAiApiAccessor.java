package cool.cena.openai;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cool.cena.openai.exception.*;
import cool.cena.openai.exception.audio.*;
import cool.cena.openai.exception.chatcompletion.*;
import cool.cena.openai.exception.edit.*;
import cool.cena.openai.exception.embedding.*;
import cool.cena.openai.exception.image.*;
import cool.cena.openai.exception.moderation.*;
import cool.cena.openai.exception.textcompletion.*;
import cool.cena.openai.exception.file.*;
import cool.cena.openai.exception.finetune.CancelFineTuneResourceAccessException;
import cool.cena.openai.exception.finetune.CancelFineTuneStatusCodeException;
import cool.cena.openai.exception.finetune.CreateFineTuneResourceAccessException;
import cool.cena.openai.exception.finetune.CreateFineTuneStatusCodeException;
import cool.cena.openai.exception.finetune.DeleteFineTuneResourceAccessException;
import cool.cena.openai.exception.finetune.DeleteFineTuneStatusCodeException;
import cool.cena.openai.exception.finetune.ListFineTuneEventsResourceAccessException;
import cool.cena.openai.exception.finetune.ListFineTuneEventsStatusCodeException;
import cool.cena.openai.exception.finetune.ListFineTuneResourceAccessException;
import cool.cena.openai.exception.finetune.ListFineTuneStatusCodeException;
import cool.cena.openai.exception.finetune.RetrieveFineTuneResourceAccessException;
import cool.cena.openai.exception.finetune.RetrieveFineTuneStatusCodeException;
import cool.cena.openai.pojo.audio.OpenAiAudioTranscriptionRequestBody;
import cool.cena.openai.pojo.audio.OpenAiAudioTranslationRequestBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionRequestBody;
import cool.cena.openai.pojo.chatcompletion.OpenAiChatCompletionResponseBody;
import cool.cena.openai.pojo.edit.OpenAiEditRequestBody;
import cool.cena.openai.pojo.edit.OpenAiEditResponseBody;
import cool.cena.openai.pojo.embedding.OpenAiEmbeddingRequestBody;
import cool.cena.openai.pojo.embedding.OpenAiEmbeddingResponseBody;
import cool.cena.openai.pojo.file.OpenAiListFileResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiCancelFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiCreateFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiDeleteFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiFineTuneRequestBody;
import cool.cena.openai.pojo.finetune.OpenAiListFineTuneEventsResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiListFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiRetrieveFineTuneResponseBody;
import cool.cena.openai.pojo.file.OpenAiDeleteFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiDownloadFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiFileRequestBody;
import cool.cena.openai.pojo.file.OpenAiFile;
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
    private final String FILE_URL = "https://api.openai.com/v1/files";
    private final String FINE_TUNE_URL = "https://api.openai.com/v1/fine-tunes";

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


    // file requests
    // list file request
    public OpenAiListFileResponseBody listFiles(){

        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiListFileResponseBody> responseEntity = this.restTemplate.exchange(this.FILE_URL, HttpMethod.GET, requestEntity, OpenAiListFileResponseBody.class);
            return responseEntity.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ListFileStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ListFileResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    
    // upload file request
    public OpenAiFile createFile(OpenAiFileRequestBody requestBody){

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody.toMultiValueMap(), httpFileHeaders);

        try{

            OpenAiFile responseBody = this.restTemplate.postForObject(FILE_URL, requestEntity, OpenAiFile.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new CreateFileStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new CreateFileResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // delete file request
    public OpenAiDeleteFileResponseBody deleteFile(String fileId){

        String deleteFileUrl = this.FILE_URL + "/" + fileId;
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiDeleteFileResponseBody> responseBody = this.restTemplate.exchange(deleteFileUrl, HttpMethod.DELETE, requestEntity, OpenAiDeleteFileResponseBody.class);
            return responseBody.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new DeleteFileStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new DeleteFileResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // retrieve file request
    public OpenAiFile retrieveFile(String fileId){

        String retrieveFileUrl = this.FILE_URL + "/" + fileId;
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiFile> responseEntity = this.restTemplate.exchange(retrieveFileUrl, HttpMethod.GET, requestEntity, OpenAiFile.class);
            return responseEntity.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new RetrieveFileStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new RetrieveFileResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // download file request
    public OpenAiDownloadFileResponseBody downloadFile(String fileId){

        String retrieveFileContentUrl = this.FILE_URL + "/" + fileId + "/content";
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<String> responseEntity = this.restTemplate.exchange(retrieveFileContentUrl, HttpMethod.GET, requestEntity, String.class);
            return new OpenAiDownloadFileResponseBody(responseEntity.getBody());
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new DownloadFileStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new DownloadFileResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }



    // fine tune requests
    // create fine tune request
    public OpenAiCreateFineTuneResponseBody sendRequest(OpenAiFineTuneRequestBody requestBody){

        HttpEntity<OpenAiFineTuneRequestBody> requestEntity = new HttpEntity<>(requestBody, httpJsonHeaders);
        
        try{

            OpenAiCreateFineTuneResponseBody responseBody = this.restTemplate.postForObject(this.FINE_TUNE_URL, requestEntity, OpenAiCreateFineTuneResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new CreateFineTuneStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new CreateFineTuneResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // list fine tunes request
    public OpenAiListFineTuneResponseBody listFineTunes(){

        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiListFineTuneResponseBody> responseEntity = this.restTemplate.exchange(this.FILE_URL, HttpMethod.GET, requestEntity, OpenAiListFineTuneResponseBody.class);
            return responseEntity.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ListFineTuneStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ListFineTuneResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // retrieve fine tune request
    public OpenAiRetrieveFineTuneResponseBody retrieveFineTune(String fineTuneId){

        String retrieveFineTuneUrl = this.FINE_TUNE_URL + "/" + fineTuneId;
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiRetrieveFineTuneResponseBody> responseEntity = this.restTemplate.exchange(retrieveFineTuneUrl, HttpMethod.GET, requestEntity, OpenAiRetrieveFineTuneResponseBody.class);
            return responseEntity.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new RetrieveFineTuneStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new RetrieveFineTuneResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // cancel fine tune request
    public OpenAiCancelFineTuneResponseBody cancelFineTune(String fineTuneId){

        String cancelFineTuneUrl = this.FINE_TUNE_URL + "/" + fineTuneId + "/cancel";
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            OpenAiCancelFineTuneResponseBody responseBody = this.restTemplate.postForObject(cancelFineTuneUrl, requestEntity, OpenAiCancelFineTuneResponseBody.class);
            return responseBody;
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new CancelFineTuneStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new CancelFineTuneResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // list fine tune events request
    public OpenAiListFineTuneEventsResponseBody listFineTuneEvents(String fineTuneId){

        String listFineTuneEventsUrl = this.FINE_TUNE_URL + "/" + fineTuneId + "/events";
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiListFineTuneEventsResponseBody> responseEntity = this.restTemplate.exchange(listFineTuneEventsUrl, HttpMethod.GET, requestEntity, OpenAiListFineTuneEventsResponseBody.class);
            return responseEntity.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new ListFineTuneEventsStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new ListFineTuneEventsResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }

    // delete fine tune request
    public OpenAiDeleteFineTuneResponseBody deleteFineTune(String model){

        String deleteFineTuneUrl = "https://api.openai.com/v1/models/" + model;
        HttpEntity<String> requestEntity = new HttpEntity<>(httpFileHeaders);

        try{

            ResponseEntity<OpenAiDeleteFineTuneResponseBody> responseEntity = this.restTemplate.exchange(deleteFineTuneUrl, HttpMethod.DELETE, requestEntity, OpenAiDeleteFineTuneResponseBody.class);
            return responseEntity.getBody();
        
        }catch(HttpStatusCodeException e){

            HttpStatusCode httpStatusCode = e.getStatusCode();
            
            if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                throw new OpenAiUnauthorizedException(e.getMessage());

            }else{

                throw new DeleteFineTuneStatusCodeException(httpStatusCode, e.getMessage());

            }

        }catch(ResourceAccessException e){

            throw new DeleteFineTuneResourceAccessException(e.getMessage());

        }catch(RestClientException e){

            throw new OpenAiUnknownException(e.getMessage());

        }
    }
}