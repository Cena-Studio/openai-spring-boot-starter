package cool.cena.openai.pojo.embedding;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiEmbeddingResponseBody {

    private String id, model;
    private List<OpenAiEmbeddingResponseData> data;
    private OpenAiEmbeddingResponseUsage usage;

    // getters and setters
    public String getId(){
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel(){
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    
    public List<OpenAiEmbeddingResponseData> getData() {
        return data;
    }

    public void setData(List<OpenAiEmbeddingResponseData> data) {
        this.data = data;
    }

    public OpenAiEmbeddingResponseUsage getUsage() {
        return usage;
    }

    public void setUsage(OpenAiEmbeddingResponseUsage usage) {
        this.usage = usage;
    }

    public List<Double> getEmbedding(){
        return this.data.get(0).getEmbedding();
    }

    public static class OpenAiEmbeddingResponseData {
    
        private String object;
        @JsonProperty("category_scores")
        private List<Double> embedding;
        private int index;

        

        public String getObject() {
            return object;
        }



        public void setObject(String object) {
            this.object = object;
        }



        public List<Double> getEmbedding() {
            return embedding;
        }



        public void setEmbedding(List<Double> embedding) {
            this.embedding = embedding;
        }



        public int getIndex() {
            return index;
        }



        public void setIndex(int index) {
            this.index = index;
        }
        
    }

    public static class OpenAiEmbeddingResponseUsage {
    
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;
    
        // getters and setters
        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }
    }
}
