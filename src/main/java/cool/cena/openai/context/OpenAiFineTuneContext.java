package cool.cena.openai.context;


import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.autoconfigure.OpenAiProperties.OpenAiFineTuneProperties;
import cool.cena.openai.exception.file.FileInvalidException;
import cool.cena.openai.pojo.file.OpenAiListFileResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiCreateFineTuneResponseBody;
import cool.cena.openai.pojo.finetune.OpenAiFineTuneRequestBody;
import cool.cena.openai.pojo.file.OpenAiDeleteFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiDownloadFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiFileRequestBody;
import cool.cena.openai.pojo.file.OpenAiFile;

public class OpenAiFineTuneContext {

    private OpenAiApiAccessor apiAccessor;
    private OpenAiFineTuneRequestBody requestBody;

    // constructor
    public OpenAiFineTuneContext(OpenAiApiAccessor apiAccessor, OpenAiFineTuneProperties properties) {
        this.apiAccessor = apiAccessor;
        this.requestBody = new OpenAiFineTuneRequestBody(properties);
    }

    public OpenAiCreateFineTuneResponseBody createFineTune(String trainingFileId){
        requestBody.setTrainingFile(trainingFileId);
        return null;
    }

    public OpenAiCreateFineTuneResponseBody createFineTune(String trainingFileId, String validationFileId){
        requestBody.setTrainingFile(trainingFileId);
        requestBody.setValidationFile(validationFileId);
        return null;
    }
}
