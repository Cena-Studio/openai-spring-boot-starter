package cool.cena.openai.pojo.textcompletion;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiTextCompletionProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiTextCompletionRequestBody {
    private String model, suffix, user, prompt;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    private Integer n;
    private Integer logprobs;
    private boolean echo;
    private List<String> stop;
    @JsonProperty("presence_penalty")
    private Double presencePenalty;
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;
    @JsonProperty("best_of")
    private Integer bestOf;
    @JsonProperty("logit_bias")
    Map<Integer, Double> logitBias;

    public OpenAiTextCompletionRequestBody(OpenAiTextCompletionProperties textCompletionProperties) {
        this.model = textCompletionProperties.getModel();
        this.suffix = textCompletionProperties.getSuffix();
        this.user = textCompletionProperties.getUser();
        this.maxTokens = textCompletionProperties.getMaxTokens();
        this.temperature = textCompletionProperties.getTemperature();
        this.topP = textCompletionProperties.getTopP();
        this.n = textCompletionProperties.getN();
        this.logprobs = textCompletionProperties.getLogprobs();
        this.echo = textCompletionProperties.isEcho();
        this.stop = textCompletionProperties.getStop();
        this.presencePenalty = textCompletionProperties.getPresencePenalty();
        this.frequencyPenalty = textCompletionProperties.getFrequencyPenalty();
        this.bestOf = textCompletionProperties.getBestOf();
        this.logitBias = textCompletionProperties.getLogitBias();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Integer getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Integer logprobs) {
        this.logprobs = logprobs;
    }

    public Integer getBestOf() {
        return bestOf;
    }

    public void setBestOf(Integer bestOf) {
        this.bestOf = bestOf;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public boolean isEcho() {
        return echo;
    }

    public void setEcho(boolean echo) {
        this.echo = echo;
    }

    public List<String> getStop() {
        return stop;
    }

    public void setStop(List<String> stop) {
        this.stop = stop;
    }

    public Double getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(Double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public Double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(Double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public Map<Integer, Double> getLogitBias() {
        return logitBias;
    }

    public void setLogitBias(Map<Integer, Double> logitBias) {
        this.logitBias = logitBias;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

}
