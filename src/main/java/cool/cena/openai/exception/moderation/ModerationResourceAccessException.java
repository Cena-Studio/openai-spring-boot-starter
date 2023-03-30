package cool.cena.openai.exception.moderation;

import cool.cena.openai.exception.OpenAiException;

public class ModerationResourceAccessException extends OpenAiException {
    public ModerationResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Moderation Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Moderation Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Moderation Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
