package cool.cena.openai.exception;

public class OpenAiUnknownException extends OpenAiException {
    public OpenAiUnknownException(String originalMessage){
        super(
            "\n[OpenAi Error] UNKNOWN ERROR." +
            "\n[OpenAi Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
