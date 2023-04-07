package cool.cena.openai.exception.finetune;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class CancelFineTuneStatusCodeException extends OpenAiException {
    public CancelFineTuneStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Cancel Fine Tune Error] " + httpStatusCode + "." +
            "\n[OpenAi Cancel Fine Tune Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Cancel Fine Tune Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
