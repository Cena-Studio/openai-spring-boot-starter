package cool.cena.openai.exception.chatcompletion;

import cool.cena.openai.exception.OpenAiException;

public class ChatCompletionOutDatedException extends OpenAiException {
    public ChatCompletionOutDatedException(){
        super(
            "\n[OpenAi Chat Completion Error] Request Outdated." +
            "\n[OpenAi Chat Completion Error] This request has been deprecated because it has been superseded by a new request." +
            "\n"
        );
    }
}
