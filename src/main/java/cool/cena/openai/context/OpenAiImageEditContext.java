package cool.cena.openai.context;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageEditProperties;
import cool.cena.openai.exception.image.ImageFileInvalidException;
import cool.cena.openai.pojo.image.OpenAiImageEditRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageEditResponseBody;

public class OpenAiImageEditContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiImageEditRequestBody requestBody;

    // constructor
    public OpenAiImageEditContext(OpenAiApiAccessor apiAccessor, OpenAiImageEditProperties openAiImageEditProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiImageEditRequestBody(openAiImageEditProperties);

    }

    // set request parameters
    public OpenAiImageEditContext setN(Integer n) {
        this.requestBody.setN(n);
        return this;
    }
    public OpenAiImageEditContext setSize(String size) {
        this.requestBody.setSize(size);
        return this;
    }
    public OpenAiImageEditContext setResponseFormat(String responseFormat) {
        this.requestBody.setResponseFormat(responseFormat);
        return this;
    }
    public OpenAiImageEditContext setUser(String user) {
        this.requestBody.setUser(user);
        return this;
    }
    

    // make a request
    public OpenAiImageEditResponseBody create(Object image, String prompt){

        Resource imageResource = this.resourcify(image);
        this.requestBody.setImage(imageResource);
        this.requestBody.setPrompt(prompt);
        return apiAccessor.sendRequest(requestBody);
    }

    public OpenAiImageEditResponseBody create(Object image, Object mask, String prompt){

        Resource imageResource = this.resourcify(image);
        Resource maskResource = this.resourcify(mask);
        this.requestBody.setImage(imageResource);
        this.requestBody.setMask(maskResource);
        this.requestBody.setPrompt(prompt);
        return apiAccessor.sendRequest(requestBody);
    }

    private Resource resourcify(Object imageObject){
        
        if(imageObject instanceof String){

            String imageString = (String) imageObject;
            // try if the imageObject is a base64 string
            try {

                byte[] imageBytes = Base64.getDecoder().decode((String) imageObject);
                return new ByteArrayResource(imageBytes);

            // if not, it could be a local path or a url
            } catch (IllegalArgumentException e) {

                File imageFile = new File(imageString);
                // it is a valid local path
                if (imageFile.exists()) {
                    
                    return new FileSystemResource(imageFile);

                // it is not a valid local path, maybe it is a url
                } else {

                    try {

                        return new UrlResource(imageString);

                    // it is not a valid image string
                    } catch (MalformedURLException f) {
                        
                        throw new ImageFileInvalidException(f.getMessage());
                    }

                }

            }

        }else if(imageObject instanceof byte[]){

            return new ByteArrayResource((byte[]) imageObject);

        }else if(imageObject instanceof File){

            return new FileSystemResource((File) imageObject);

        }else if(imageObject instanceof FileSystemResource){

            return (FileSystemResource) imageObject;

        }else if(imageObject instanceof UrlResource){

            return (UrlResource) imageObject;

        }else if(imageObject instanceof ByteArrayResource){

            return (ByteArrayResource) imageObject;

        }
        
        // the type of the input image object parameter is invalid
        throw new ImageFileInvalidException();
    }

}
