package cool.cena.openai.exception.finetune;

import cool.cena.openai.exception.OpenAiException;

public class CancelFineTuneResourceAccessException extends OpenAiException {
    public CancelFineTuneResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Cancel Fine Tune Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Cancel Fine Tune Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Cancel Fine Tune Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
