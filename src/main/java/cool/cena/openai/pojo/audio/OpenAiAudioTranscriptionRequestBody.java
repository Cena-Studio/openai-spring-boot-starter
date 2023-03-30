package cool.cena.openai.pojo.audio;

import org.springframework.util.MultiValueMap;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiAudioTranscriptionProperties;

public class OpenAiAudioTranscriptionRequestBody extends OpenAiAudioRequestBody {

    String language;

    public OpenAiAudioTranscriptionRequestBody(OpenAiAudioTranscriptionProperties audioTranscriptionProperties) {
        super(audioTranscriptionProperties);
        this.language = audioTranscriptionProperties.getLanguage();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public MultiValueMap<String, Object> toMultiValueMap(){

        MultiValueMap<String, Object> multiValueMap = super.toMultiValueMap();

        if(this.language != null){
            multiValueMap.add("language", this.language);
        }
        
        return multiValueMap;
    }
}
