package cool.cena.openai.exception.file;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class RetrieveFileStatusCodeException extends OpenAiException {
    public RetrieveFileStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Retrieve File Error] " + httpStatusCode + "." +
            "\n[OpenAi Retrieve File Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Retrieve File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
