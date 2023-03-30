package cool.cena.openai.exception.image;

import cool.cena.openai.exception.OpenAiException;

public class ImageResourceAccessException extends OpenAiException {
    public ImageResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Image Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Image Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Image Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
