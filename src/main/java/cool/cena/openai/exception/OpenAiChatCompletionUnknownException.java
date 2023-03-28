package cool.cena.openai.exception;

public class OpenAiChatCompletionUnknownException extends OpenAiChatCompletionException {
    public OpenAiChatCompletionUnknownException(String message){
        super("\n[OpenAi Chat Completion Error] UNKNOWN ERROR.\n[OpenAi Chat Completion Error] Sorry. The possible causes for this exception has not been included yet.\n[OpenAi Chat Completion Error] The fowllowing is the original exception message: \n" + message);
    }
}
