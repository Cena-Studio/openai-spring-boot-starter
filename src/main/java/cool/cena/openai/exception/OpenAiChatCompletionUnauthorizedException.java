package cool.cena.openai.exception;

public class OpenAiChatCompletionUnauthorizedException extends OpenAiChatCompletionException {
    public OpenAiChatCompletionUnauthorizedException(){
        super("\n[OpenAi Chat Completion Error] 401 UNAUTHORIZED. The following might be the cause:\n[OpenAi Chat Completion Error] - The value of `openai.key` parameter in the configuration file is incorrect.\n");
    }
}
