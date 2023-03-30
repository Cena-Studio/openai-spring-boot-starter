package cool.cena.openai.context;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiAudioTranscriptionProperties;
import cool.cena.openai.exception.audio.AudioFileInvalidException;
import cool.cena.openai.pojo.audio.OpenAiAudioTranscriptionRequestBody;
import cool.cena.openai.pojo.audio.OpenAiAudioTranscriptionResponseBody;

public class OpenAiAudioTranscriptionContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiAudioTranscriptionRequestBody requestBody;

    // constructor
    public OpenAiAudioTranscriptionContext(OpenAiApiAccessor apiAccessor, OpenAiAudioTranscriptionProperties audioTranscriptionProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiAudioTranscriptionRequestBody(audioTranscriptionProperties);
    }

    // set request parameters
    public OpenAiAudioTranscriptionContext setModel(String model) {
        this.requestBody.setModel(model);
        return this;
    }
    public OpenAiAudioTranscriptionContext setTemperature(Double temperature) {
        this.requestBody.setTemperature(temperature);
        return this;
    }
    public OpenAiAudioTranscriptionContext setResponseFormat(String responseFormat) {
        this.requestBody.setResponseFormat(responseFormat);
        return this;
    }
    public OpenAiAudioTranscriptionContext setLanguage(String language) {
        this.requestBody.setLanguage(language);
        return this;
    }

    public OpenAiAudioTranscriptionResponseBody create(Object file){

        return this.create(file, "");

    }


    public OpenAiAudioTranscriptionResponseBody create(Object file, String prompt){

        Resource fileResource = this.resourcify(file);
        this.requestBody.setFile(fileResource);
        this.requestBody.setPrompt(prompt);
        return apiAccessor.sendRequest(requestBody);
    }

    private Resource resourcify(Object audioObject){
        
        if(audioObject instanceof String){

            String audioString = (String) audioObject;
            // try if the audioObject is a base64 string
            try {

                byte[] audioBytes = Base64.getDecoder().decode((String) audioObject);
                return new ByteArrayResource(audioBytes);

            // if not, it could be a local path or a url
            } catch (IllegalArgumentException e) {

                File audioFile = new File(audioString);
                // it is a valid local path
                if (audioFile.exists()) {
                    
                    return new FileSystemResource(audioFile);

                // it is not a valid local path, maybe it is a url
                } else {

                    try {

                        return new UrlResource(audioString);

                    // it is not a valid audio string
                    } catch (MalformedURLException f) {
                        
                        throw new AudioFileInvalidException(f.getMessage());
                    }

                }

            }

        }else if(audioObject instanceof byte[]){

            return new ByteArrayResource((byte[]) audioObject);

        }else if(audioObject instanceof File){

            return new FileSystemResource((File) audioObject);

        }else if(audioObject instanceof FileSystemResource){

            return (FileSystemResource) audioObject;

        }else if(audioObject instanceof UrlResource){

            return (UrlResource) audioObject;

        }else if(audioObject instanceof ByteArrayResource){

            return (ByteArrayResource) audioObject;

        }
        
        // the type of the input audio object parameter is invalid
        throw new AudioFileInvalidException();
    }

}
