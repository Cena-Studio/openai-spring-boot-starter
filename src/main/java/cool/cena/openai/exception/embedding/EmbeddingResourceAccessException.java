package cool.cena.openai.exception.embedding;

import cool.cena.openai.exception.OpenAiException;

public class EmbeddingResourceAccessException extends OpenAiException {
    public EmbeddingResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Embedding Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Embedding Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Embedding Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
