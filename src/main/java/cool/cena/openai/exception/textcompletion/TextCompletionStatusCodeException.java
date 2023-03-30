package cool.cena.openai.exception.textcompletion;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class TextCompletionStatusCodeException extends OpenAiException {
    public TextCompletionStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Text Completion Error] " + httpStatusCode + "." +
            "\n[OpenAi Text Completion Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Text Completion Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
