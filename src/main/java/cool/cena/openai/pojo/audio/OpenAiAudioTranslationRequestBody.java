package cool.cena.openai.pojo.audio;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiAudioTranslationProperties;

public class OpenAiAudioTranslationRequestBody extends OpenAiAudioRequestBody {

    public OpenAiAudioTranslationRequestBody(OpenAiAudioTranslationProperties audioTranslationProperties) {
        super(audioTranslationProperties);
    }
}
