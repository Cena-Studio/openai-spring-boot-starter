package cool.cena.openai.exception.edit;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class EditStatusCodeException extends OpenAiException {
    public EditStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Edit Error] " + httpStatusCode + "." +
            "\n[OpenAi Edit Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Edit Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
