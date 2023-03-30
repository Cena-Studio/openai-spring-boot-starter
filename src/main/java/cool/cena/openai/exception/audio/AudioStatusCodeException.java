package cool.cena.openai.exception.audio;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class AudioStatusCodeException extends OpenAiException {
    public AudioStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Audio Error] " + httpStatusCode + "." +
            "\n[OpenAi Audio Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Audio Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
