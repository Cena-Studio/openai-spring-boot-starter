package cool.cena.openai.exception.image;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class ImageStatusCodeException extends OpenAiException {
    public ImageStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Image Error] " + httpStatusCode + "." +
            "\n[OpenAi Image Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Image Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
