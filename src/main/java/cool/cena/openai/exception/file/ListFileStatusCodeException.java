package cool.cena.openai.exception.file;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class ListFileStatusCodeException extends OpenAiException {
    public ListFileStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi List File Error] " + httpStatusCode + "." +
            "\n[OpenAi List File Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi List File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
