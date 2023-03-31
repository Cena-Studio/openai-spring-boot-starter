package cool.cena.openai.pojo.audio;

import java.util.List;

public class OpenAiAudioTranscriptionResponseBody extends OpenAiAudioResponseBody {

    private List<OpenAiAudioTranscriptionResponseSegment> segments;

    public List<OpenAiAudioTranscriptionResponseSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<OpenAiAudioTranscriptionResponseSegment> segments) {
        this.segments = segments;
    }

    public static class OpenAiAudioTranscriptionResponseSegment extends OpenAiAudioResponseBodySegment{}
    
}
