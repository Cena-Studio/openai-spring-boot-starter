package cool.cena.openai.exception.moderation;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class ModerationStatusCodeException extends OpenAiException {
    public ModerationStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Moderation Error] " + httpStatusCode + "." +
            "\n[OpenAi Moderation Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Moderation Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
