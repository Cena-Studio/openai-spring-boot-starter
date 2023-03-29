package cool.cena.openai.pojo.moderation;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiModerationProperties;

public class OpenAiModerationRequestBody {
    private String input, model;

    public OpenAiModerationRequestBody(OpenAiModerationProperties moderationProperties) {
        this.model = moderationProperties.getModel();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


}
