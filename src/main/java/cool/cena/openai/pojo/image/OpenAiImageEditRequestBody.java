package cool.cena.openai.pojo.image;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.annotation.JsonInclude;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiImageEditProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public void setImage(Resourcifyable image) {
        this.image = image.toResource();
    }

    public Resource getMask() {
        return mask;
    }

    public void setMask(Resourcifyable mask) {
        this.mask = mask.toResource();
    }
}
