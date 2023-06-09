package cool.cena.openai.pojo.moderation;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiModerationResponseBody {

    private String id, model;
    private List<OpenAiModerationResponseResult> results;

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

    public List<OpenAiModerationResponseResult> getResults() {
        return results;
    }

    public void setResults(List<OpenAiModerationResponseResult> results) {
        this.results = results;
    }

    public boolean getBooleanResults(String classification){
        return this.getResults().get(0).getCategories().get(classification);
    }

    public Double getScoreResults(String classification){
        return this.getResults().get(0).getCategoryScores().get(classification);
    }

    public boolean isFlagged(){
        return this.getResults().get(0).isFlagged();
    }
 
    public static class OpenAiModerationResponseResult {
    
        private Map<String, Boolean> categories;
        @JsonProperty("category_scores")
        private Map<String, Double> categoryScores;
        private boolean flagged;
        
        public Map<String, Boolean> getCategories() {
            return categories;
        }
        public void setCategories(Map<String, Boolean> categories) {
            this.categories = categories;
        }
        public Map<String, Double> getCategoryScores() {
            return categoryScores;
        }
        public void setCategoryScores(Map<String, Double> categoryScores) {
            this.categoryScores = categoryScores;
        }
        public boolean isFlagged() {
            return flagged;
        }
        public void setFlagged(boolean flagged) {
            this.flagged = flagged;
        }

        
    }
}
