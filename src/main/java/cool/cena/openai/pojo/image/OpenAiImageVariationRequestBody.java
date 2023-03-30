package cool.cena.openai.pojo.image;

import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageVariationProperties;

public class OpenAiImageVariationRequestBody extends OpenAiImageRequestBody {

    private Resource image;

    public OpenAiImageVariationRequestBody(OpenAiImageVariationProperties imageVariationProperties) {
        super(imageVariationProperties);
    }
    
    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    public MultiValueMap<String, Object> toMultiValueMap(){

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        
        multiValueMap.add("image", image);
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
