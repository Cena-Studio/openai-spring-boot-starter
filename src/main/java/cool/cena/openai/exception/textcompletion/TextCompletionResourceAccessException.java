package cool.cena.openai.exception.textcompletion;

import cool.cena.openai.exception.OpenAiException;

public class TextCompletionResourceAccessException extends OpenAiException {
    public TextCompletionResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Text Completion Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Text Completion Error] - Requested for a completion but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Text Completion Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
