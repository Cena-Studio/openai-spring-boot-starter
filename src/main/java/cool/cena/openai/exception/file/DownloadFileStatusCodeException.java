package cool.cena.openai.exception.file;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class DownloadFileStatusCodeException extends OpenAiException {
    public DownloadFileStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Download File Error] " + httpStatusCode + "." +
            "\n[OpenAi Download File Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Download File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
