package cool.cena.openai.exception.finetune;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class DeleteFineTuneStatusCodeException extends OpenAiException {
    public DeleteFineTuneStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Delete Fine Tune Error] " + httpStatusCode + "." +
            "\n[OpenAi Delete Fine Tune Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Delete Fine Tune Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
