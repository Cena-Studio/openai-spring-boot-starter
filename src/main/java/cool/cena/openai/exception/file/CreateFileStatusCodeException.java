package cool.cena.openai.exception.file;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class CreateFileStatusCodeException extends OpenAiException {
    public CreateFileStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Create File Error] " + httpStatusCode + "." +
            "\n[OpenAi Create File Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Create File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
