package cool.cena.openai.exception.finetune;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class ListFineTuneStatusCodeException extends OpenAiException {
    public ListFineTuneStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi List Fine Tune Error] " + httpStatusCode + "." +
            "\n[OpenAi List Fine Tune Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi List Fine Tune Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
