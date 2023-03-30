package cool.cena.openai.exception.chatcompletion;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class ChatCompletionStatusCodeException extends OpenAiException {
    public ChatCompletionStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Chat Completion Error] " + httpStatusCode + "." +
            "\n[OpenAi Chat Completion Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Chat Completion Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
