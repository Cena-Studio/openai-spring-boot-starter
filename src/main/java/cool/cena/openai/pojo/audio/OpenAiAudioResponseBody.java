package cool.cena.openai.pojo.audio;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiAudioResponseBody {

    private String task, language, text;
    private Double duration;

    

    public String getTask() {
        return task;
    }



    public void setTask(String task) {
        this.task = task;
    }



    public String getLanguage() {
        return language;
    }



    public void setLanguage(String language) {
        this.language = language;
    }



    public String getText() {
        return text;
    }



    public void setText(String text) {
        this.text = text;
    }



    public Double getDuration() {
        return duration;
    }



    public void setDuration(Double duration) {
        this.duration = duration;
    }



    public static class OpenAiAudioResponseBodySegment{
        Integer id, seek;
        Double start, end, temperature;
        String text;
        List<Integer> tokens;
        @JsonProperty("avg_logprob")
        Double avgLogprob;
        @JsonProperty("compression_ratio")
        Double compressionRatio;
        @JsonProperty("no_speech_prob")
        Double noSpeechProb;
        @JsonProperty("transient")
        Boolean trans;

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getSeek() {
            return seek;
        }
        public void setSeek(Integer seek) {
            this.seek = seek;
        }
        public Double getStart() {
            return start;
        }
        public void setStart(Double start) {
            this.start = start;
        }
        public Double getEnd() {
            return end;
        }
        public void setEnd(Double end) {
            this.end = end;
        }
        public Double getTemperature() {
            return temperature;
        }
        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public List<Integer> getTokens() {
            return tokens;
        }
        public void setTokens(List<Integer> tokens) {
            this.tokens = tokens;
        }
        public Double getAvgLogprob() {
            return avgLogprob;
        }
        public void setAvgLogprob(Double avgLogprob) {
            this.avgLogprob = avgLogprob;
        }
        public Double getCompressionRatio() {
            return compressionRatio;
        }
        public void setCompressionRatio(Double compressionRatio) {
            this.compressionRatio = compressionRatio;
        }
        public Double getNoSpeechProb() {
            return noSpeechProb;
        }
        public void setNoSpeechProb(Double noSpeechProb) {
            this.noSpeechProb = noSpeechProb;
        }
        public Boolean getTrans() {
            return trans;
        }
        public void setTrans(Boolean trans) {
            this.trans = trans;
        }

        
    }
    
}
