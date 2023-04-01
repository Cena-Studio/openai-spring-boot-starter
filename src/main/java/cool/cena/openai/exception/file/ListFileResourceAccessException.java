package cool.cena.openai.exception.file;

import cool.cena.openai.exception.OpenAiException;

public class ListFileResourceAccessException extends OpenAiException {
    public ListFileResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi List File Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi List File Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi List File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
