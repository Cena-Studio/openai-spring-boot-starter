package cool.cena.openai.exception.finetune;

import cool.cena.openai.exception.OpenAiException;

public class DeleteFineTuneResourceAccessException extends OpenAiException {
    public DeleteFineTuneResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Delete Fine Tune Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Delete Fine Tune Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Delete Fine Tune Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
