package cool.cena.openai.exception;

public class OpenAiChatCompletionBadRequestException extends OpenAiChatCompletionException {
    public OpenAiChatCompletionBadRequestException(){
        super("\n[OpenAi Chat Completion Error] 400 BAD REQUEST. The following might be the cause:\n[OpenAi Chat Completion Error] - The total tokens of the dialogue context in the current request might exceed the model's limit.\n");
    }
}
