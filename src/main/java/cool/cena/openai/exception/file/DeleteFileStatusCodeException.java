package cool.cena.openai.exception.file;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class DeleteFileStatusCodeException extends OpenAiException {
    public DeleteFileStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Delete File Error] " + httpStatusCode + "." +
            "\n[OpenAi Delete File Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Delete File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
