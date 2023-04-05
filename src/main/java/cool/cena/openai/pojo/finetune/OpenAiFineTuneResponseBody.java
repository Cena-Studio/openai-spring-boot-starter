package cool.cena.openai.pojo.finetune;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cool.cena.openai.pojo.file.OpenAiFile;

public class OpenAiFineTuneResponseBody {
    private String id, object, model, status;
    @JsonProperty("fine_tuned_model")
    private String fineTunedModel;
    @JsonProperty("organization_id")
    private String organizationId;
    private OpenAiFineTuneResponseHyperparams hyperparams;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;
    @JsonProperty("result_files")
    List<OpenAiFile> resultFiles;
    @JsonProperty("validation_files")
    List<OpenAiFile> validationFiles;
    @JsonProperty("training_files")
    List<OpenAiFile> trainingFiles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public String getFineTunedModel() {
        return fineTunedModel;
    }

    public void setFineTunedModel(String fineTunedModel) {
        this.fineTunedModel = fineTunedModel;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public OpenAiFineTuneResponseHyperparams getHyperparams() {
        return hyperparams;
    }

    public void setHyperparams(OpenAiFineTuneResponseHyperparams hyperparams) {
        this.hyperparams = hyperparams;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OpenAiFile> getResultFiles() {
        return resultFiles;
    }

    public void setResultFiles(List<OpenAiFile> resultFiles) {
        this.resultFiles = resultFiles;
    }

    public List<OpenAiFile> getValidationFiles() {
        return validationFiles;
    }

    public void setValidationFiles(List<OpenAiFile> validationFiles) {
        this.validationFiles = validationFiles;
    }

    public List<OpenAiFile> getTrainingFiles() {
        return trainingFiles;
    }

    public void setTrainingFiles(List<OpenAiFile> trainingFiles) {
        this.trainingFiles = trainingFiles;
    }

    public static class OpenAiFineTuneResponseHyperparams{
        
        @JsonProperty("batch_size")
        private Integer batchSize;
        @JsonProperty("learning_rate_multiplier")
        private Integer learningRateMultiplier;
        @JsonProperty("n_epochs")
        private Integer nEpochs;
        @JsonProperty("prompt_loss_weight")
        private Integer promptLossWeight;
        public Integer getBatchSize() {
            return batchSize;
        }
        public void setBatchSize(Integer batchSize) {
            this.batchSize = batchSize;
        }
        public Integer getLearningRateMultiplier() {
            return learningRateMultiplier;
        }
        public void setLearningRateMultiplier(Integer learningRateMultiplier) {
            this.learningRateMultiplier = learningRateMultiplier;
        }
        public Integer getnEpochs() {
            return nEpochs;
        }
        public void setnEpochs(Integer nEpochs) {
            this.nEpochs = nEpochs;
        }
        public Integer getPromptLossWeight() {
            return promptLossWeight;
        }
        public void setPromptLossWeight(Integer promptLossWeight) {
            this.promptLossWeight = promptLossWeight;
        }

        
    }
    
}
