package cool.cena.openai.pojo.image;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.annotation.JsonInclude;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageGenerationProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiImageVariationRequestBody extends OpenAiImageRequestBody {

    private Resource image;

    public OpenAiImageVariationRequestBody(OpenAiImageGenerationProperties imageGenerationProperties) {
        super(imageGenerationProperties);
    }
    
    public Resource getImage() {
        return image;
    }

    public void setImage(Resourcifyable image) {
        this.image = image.toResource();
    }
}
