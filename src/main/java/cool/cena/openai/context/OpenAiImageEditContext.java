package cool.cena.openai.context;

import java.io.File;
import java.util.Base64;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageEditProperties;
import cool.cena.openai.pojo.image.ByteArrayImageResource;
import cool.cena.openai.pojo.image.FileImageResource;
import cool.cena.openai.pojo.image.OpenAiImageEditRequestBody;
import cool.cena.openai.pojo.image.OpenAiImageEditResponseBody;
import cool.cena.openai.pojo.image.Resourcifyable;
import cool.cena.openai.pojo.image.UrlImageResource;
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

        Resourcifyable resourcifyableImage = this.resourcify(image);
        this.requestBody.setImage(resourcifyableImage);
        this.requestBody.setPrompt(prompt);
        return apiAccessor.sendRequest(requestBody);
    }

    public OpenAiImageEditResponseBody create(Object image, Object mask, String prompt){

        Resourcifyable resourcifyableImage = this.resourcify(image);
        Resourcifyable resourcifyableMask = this.resourcify(mask);
        this.requestBody.setImage(resourcifyableImage);
        this.requestBody.setMask(resourcifyableMask);
        this.requestBody.setPrompt(prompt);
        return apiAccessor.sendRequest(requestBody);
    }

    private Resourcifyable resourcify(Object imageObject){
        
        if(imageObject instanceof String){

            String imageString = (String) imageObject;
            // try if the imageObject is a base64 string
            try {
                byte[] imageBytes = Base64.getDecoder().decode((String) imageObject);
                return new ByteArrayImageResource(imageBytes);

            // if not, it could be a local path or a url
            } catch (IllegalArgumentException e) {

                File imageFile = new File(imageString);
                // it is a valid local path
                if (imageFile.exists()) {
                    
                    return new FileImageResource(imageFile);

                // it is not a valid local path, maybe it is a url
                } else {
                    return new UrlImageResource(imageString);
                }
            }

        }else if(imageObject instanceof File){

            return new FileImageResource((File) imageObject);

        }

        return null;
    };

}
