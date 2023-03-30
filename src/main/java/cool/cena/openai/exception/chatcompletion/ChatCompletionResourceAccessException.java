package cool.cena.openai.exception.chatcompletion;

import cool.cena.openai.exception.OpenAiException;

public class ChatCompletionResourceAccessException extends OpenAiException {
    public ChatCompletionResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Chat Completion Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Chat Completion Error] - Requested a completion but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Chat Completion Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
