package cool.cena.openai.pojo.image;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageEditProperties;

public class OpenAiImageEditRequestBody extends OpenAiImageRequestBody {

    private Resource image, mask;
    private String prompt;

    public OpenAiImageEditRequestBody(OpenAiImageEditProperties imageEditProperties) {
        super(imageEditProperties);
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    public Resource getMask() {
        return mask;
    }

    public void setMask(Resource mask) {
        this.mask = mask;
    }

    public MultiValueMap<String, Object> toMultiValueMap(){

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("image", image);
        multiValueMap.add("prompt", prompt);
        if (mask != null) {
            multiValueMap.add("mask", mask);
        }
        if (super.getN() != null) {
            multiValueMap.add("n", super.getN());
        }
        if (super.getResponseFormat() != null) {
            multiValueMap.add("response_format", super.getResponseFormat());
        }
        if (super.getSize() != null) {
            multiValueMap.add("size", super.getSize());
        }
        if (super.getUser() != null) {
            multiValueMap.add("user", super.getUser());
        }

        return multiValueMap;

    }
}
