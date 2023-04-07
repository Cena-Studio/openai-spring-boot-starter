package cool.cena.openai.exception.finetune;

import cool.cena.openai.exception.OpenAiException;

public class RetrieveFineTuneResourceAccessException extends OpenAiException {
    public RetrieveFineTuneResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Retrieve Fine Tune Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Retrieve Fine Tune Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Retrieve Fine Tune Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
