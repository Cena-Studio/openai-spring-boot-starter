package cool.cena.openai.context;


import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiFineTuneProperties;
import cool.cena.openai.pojo.finetune.OpenAiCancelFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiCreateFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiDeleteFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiFineTuneRequestBody;
import cool.cena.openai.pojo.finetune.OpenAiListFineTuneEventsResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiListFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiRetrieveFineTuneResponseBody;

public class OpenAiFineTuneContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiFineTuneRequestBody requestBody;

    // constructor
    public OpenAiFineTuneContext(OpenAiApiAccessor apiAccessor, OpenAiFineTuneProperties properties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiFineTuneRequestBody(properties);
    }

    public OpenAiCreateFineTuneResponseBody createFineTune(String trainingFileId){
        return this.createFineTune(trainingFileId, null);
    }

    public OpenAiCreateFineTuneResponseBody createFineTune(String trainingFileId, String validationFileId){
        requestBody.setTrainingFile(trainingFileId);
        requestBody.setValidationFile(validationFileId);
        return apiAccessor.sendRequest(requestBody);
    }

    public OpenAiListFineTuneResponseBody listFineTunes(){
        return apiAccessor.listFineTunes();
    }

    public OpenAiRetrieveFineTuneResponseBody retreiveFineTune(String fineTuneId){
        return apiAccessor.retrieveFineTune(fineTuneId);
    }

    public OpenAiCancelFineTuneResponseBody cancelFineTune(String fineTuneId){
        return apiAccessor.cancelFineTune(fineTuneId);
    }

    public OpenAiListFineTuneEventsResponseBody listFineTuneEvents(String fineTuneId){
        return apiAccessor.listFineTuneEvents(fineTuneId);
    }

    public OpenAiDeleteFineTuneResponseBody deleteFineTune(String model){
        return apiAccessor.deleteFineTune(model);
    }
}
