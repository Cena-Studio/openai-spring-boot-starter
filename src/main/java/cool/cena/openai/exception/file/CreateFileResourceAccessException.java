package cool.cena.openai.exception.file;

import cool.cena.openai.exception.OpenAiException;

public class CreateFileResourceAccessException extends OpenAiException {
    public CreateFileResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Create File Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Create File Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Create File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
