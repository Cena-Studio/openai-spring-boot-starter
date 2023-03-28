package cool.cena.openai.exception;

import org.springframework.http.HttpStatusCode;

public class OpenAiChatCompletionStatusCodeException extends OpenAiChatCompletionException {
    public OpenAiChatCompletionStatusCodeException(HttpStatusCode httpStatusCode, String message){
        super("\n[OpenAi Chat Completion Error] " + httpStatusCode + ".\n[OpenAi Chat Completion Error] Sorry. The possible causes for this exception has not been included yet.\n[OpenAi Chat Completion Error] The fowllowing is the original exception message: \n" + message);
    }
}
