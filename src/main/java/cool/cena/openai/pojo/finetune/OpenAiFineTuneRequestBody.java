package cool.cena.openai.pojo.finetune;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiFineTuneProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenAiFineTuneRequestBody {

    @JsonProperty("training_file")
    private String trainingFile;
    @JsonProperty("validation_file")
    private String validationFile;
    private String model, suffix;
    @JsonProperty("classification_positive_class")
    private String classificationPositiveClass;
    @JsonProperty("n_epochs")
    private Integer nEpochs;
    @JsonProperty("batch_size")
    private Integer batchSize;
    @JsonProperty("classification_n_classes")
    private Integer classificationNClasses;
    @JsonProperty("learning_rate_multiplier")
    private Double learningRateMultiplier;
    @JsonProperty("prompt_loss_weight")
    private Double promptLossWeight;
    @JsonProperty("compute_classification_metrics")
    private Boolean computeClassificationMetrics;
    @JsonProperty("classification_betas")
    private Double[] classificationBetas;

    public OpenAiFineTuneRequestBody(OpenAiFineTuneProperties fineTuneProperties) {
        this.model = fineTuneProperties.getModel();
        this.nEpochs = fineTuneProperties.getnEpochs();
        this.batchSize = fineTuneProperties.getBatchSize();
        this.learningRateMultiplier = fineTuneProperties.getLearningRateMultiplier();
        this.promptLossWeight = fineTuneProperties.getPromptLossWeight();
        this.computeClassificationMetrics = fineTuneProperties.getComputeClassificationMetrics();
        this.classificationNClasses = fineTuneProperties.getClassificationNClasses();
        this.classificationPositiveClass = fineTuneProperties.getClassificationPositiveClass();
        this.classificationBetas = fineTuneProperties.getClassificationBetas();
        this.suffix = fineTuneProperties.getSuffix();
    }

    public String getTrainingFile() {
        return trainingFile;
    }
    public void setTrainingFile(String trainingFile) {
        this.trainingFile = trainingFile;
    }
    public String getValidationFile() {
        return validationFile;
    }
    public void setValidationFile(String validationFile) {
        this.validationFile = validationFile;
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
    public String getClassificationPositiveClass() {
        return classificationPositiveClass;
    }
    public void setClassificationPositiveClass(String classificationPositiveClass) {
        this.classificationPositiveClass = classificationPositiveClass;
    }
    public Integer getnEpochs() {
        return nEpochs;
    }
    public void setnEpochs(Integer nEpochs) {
        this.nEpochs = nEpochs;
    }
    public Integer getBatchSize() {
        return batchSize;
    }
    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
    public Integer getClassificationNClasses() {
        return classificationNClasses;
    }
    public void setClassificationNClasses(Integer classificationNClasses) {
        this.classificationNClasses = classificationNClasses;
    }
    public Double getLearningRateMultiplier() {
        return learningRateMultiplier;
    }
    public void setLearningRateMultiplier(Double learningRateMultiplier) {
        this.learningRateMultiplier = learningRateMultiplier;
    }
    public Double getPromptLossWeight() {
        return promptLossWeight;
    }
    public void setPromptLossWeight(Double promptLossWeight) {
        this.promptLossWeight = promptLossWeight;
    }
    public Boolean getComputeClassificationMetrics() {
        return computeClassificationMetrics;
    }
    public void setComputeClassificationMetrics(Boolean computeClassificationMetrics) {
        this.computeClassificationMetrics = computeClassificationMetrics;
    }
    public Double[] getClassificationBetas() {
        return classificationBetas;
    }
    public void setClassificationBetas(Double[] classificationBetas) {
        this.classificationBetas = classificationBetas;
    }

    

}
    
