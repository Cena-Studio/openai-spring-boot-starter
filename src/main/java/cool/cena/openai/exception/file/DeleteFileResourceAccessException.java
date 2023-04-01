package cool.cena.openai.exception.file;

import cool.cena.openai.exception.OpenAiException;

public class DeleteFileResourceAccessException extends OpenAiException {
    public DeleteFileResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Delete File Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Delete File Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Delete File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
