package cool.cena.openai.exception;

public class OpenAiChatCompletionResourceAccessException extends OpenAiChatCompletionException {
    public OpenAiChatCompletionResourceAccessException(){
        super("\n[OpenAi Chat Completion Error] RESOURCE ACCESS ERROR. The following might be the cause:\n[OpenAi Chat Completion Error] - Requested a completion but did not receive any response from the OpenAi server until the connection was reset.\n");
    }
}
