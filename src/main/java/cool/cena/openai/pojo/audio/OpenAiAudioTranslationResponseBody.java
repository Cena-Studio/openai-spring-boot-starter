package cool.cena.openai.pojo.audio;

import java.util.List;

public class OpenAiAudioTranslationResponseBody extends OpenAiAudioResponseBody {

    private List<OpenAiAudioTranslationResponseSegment> segments;

    public List<OpenAiAudioTranslationResponseSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<OpenAiAudioTranslationResponseSegment> segments) {
        this.segments = segments;
    }

    public static class OpenAiAudioTranslationResponseSegment extends OpenAiAudioResponseBodySegment{}
}
