package cool.cena.openai.exception.finetune;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class ListFineTuneEventsStatusCodeException extends OpenAiException {
    public ListFineTuneEventsStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi List Fine Tune Events Error] " + httpStatusCode + "." +
            "\n[OpenAi List Fine Tune Events Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi List Fine Tune Events Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
