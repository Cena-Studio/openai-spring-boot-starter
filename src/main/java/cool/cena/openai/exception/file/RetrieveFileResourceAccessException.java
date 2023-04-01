package cool.cena.openai.exception.file;

import cool.cena.openai.exception.OpenAiException;

public class RetrieveFileResourceAccessException extends OpenAiException {
    public RetrieveFileResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Retrieve File Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Retrieve File Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Retrieve File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
