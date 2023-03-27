package cool.cena.openai.exception;

public class OpenAiChatCompletionOutDatedException extends OpenAiChatCompletionException {
    public OpenAiChatCompletionOutDatedException(){
        super("[OpenAi Chat Completion Error] Request Outdated.\n[OpenAi Chat Completion Error] This request has been deprecated because it has been superseded by a new request.");
    }
}
