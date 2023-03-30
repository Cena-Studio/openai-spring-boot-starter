package cool.cena.openai.exception.chatcompletion;

import cool.cena.openai.exception.OpenAiException;

public class ChatCompletionBadRequestException extends OpenAiException {
    public ChatCompletionBadRequestException(String originalMessage){
        super(
            "\n[OpenAi Chat Completion Error] 400 BAD REQUEST. The following might be the cause:" +
            "\n[OpenAi Chat Completion Error] - The total tokens of the dialogue context in the current request might exceed the model's limit." +
            "\n[OpenAi Chat Completion Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
