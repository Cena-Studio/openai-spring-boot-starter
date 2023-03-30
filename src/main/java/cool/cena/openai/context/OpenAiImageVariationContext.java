package cool.cena.openai.context;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageVariationProperties;
import cool.cena.openai.exception.image.ImageFileInvalidException;
import cool.cena.openai.pojo.image.OpenAiImageVariationRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageVariationResponseBody;

public class OpenAiImageVariationContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiImageVariationRequestBody requestBody;

    // constructor
    public OpenAiImageVariationContext(OpenAiApiAccessor apiAccessor, OpenAiImageVariationProperties openAiImageVariationProperties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiImageVariationRequestBody(openAiImageVariationProperties);

    }

    // set request parameters
    public OpenAiImageVariationContext setN(Integer n) {
        this.requestBody.setN(n);
        return this;
    }
    public OpenAiImageVariationContext setSize(String size) {
        this.requestBody.setSize(size);
        return this;
    }
    public OpenAiImageVariationContext setResponseFormat(String responseFormat) {
        this.requestBody.setResponseFormat(responseFormat);
        return this;
    }
    public OpenAiImageVariationContext setUser(String user) {
        this.requestBody.setUser(user);
        return this;
    }
    

    // make a request
    public OpenAiImageVariationResponseBody create(Object image){

        Resource imageResource = this.resourcify(image);
        this.requestBody.setImage(imageResource);
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

        }else if(imageObject instanceof File){

            return new FileSystemResource((File) imageObject);

        }else if(imageObject instanceof FileSystemResource){

            return (FileSystemResource) imageObject;

        }else if(imageObject instanceof UrlResource){

            return (UrlResource) imageObject;

        }else if(imageObject instanceof ByteArrayResource){

            return (ByteArrayResource) imageObject;

        }
        
        throw new ImageFileInvalidException();
    };

}
