package cool.cena.openai.exception.audio;

import cool.cena.openai.exception.OpenAiException;

public class AudioResourceAccessException extends OpenAiException {
    public AudioResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Audio Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Audio Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Audio Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
