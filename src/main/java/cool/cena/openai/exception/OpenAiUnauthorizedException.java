package cool.cena.openai.exception;

public class OpenAiUnauthorizedException extends OpenAiException {
    public OpenAiUnauthorizedException(String originalMessage){
        super(
            "\n[OpenAi Error] 401 UNAUTHORIZED. The following might be the cause:" +
            "\n[OpenAi Error] - The value of `openai.key` parameter in the configuration file is incorrect." +
            "\n[OpenAi Image Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
