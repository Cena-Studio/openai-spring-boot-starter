package cool.cena.openai.exception.file;

import cool.cena.openai.exception.OpenAiException;

public class DownloadFileResourceAccessException extends OpenAiException {
    public DownloadFileResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Download File Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Download File Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Download File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
