package cool.cena.openai.exception.embedding;

import org.springframework.http.HttpStatusCode;

import cool.cena.openai.exception.OpenAiException;

public class EmbeddingStatusCodeException extends OpenAiException {
    public EmbeddingStatusCodeException(HttpStatusCode httpStatusCode, String originalMessage){
        super(
            "\n[OpenAi Embedding Error] " + httpStatusCode + "." +
            "\n[OpenAi Embedding Error] Sorry. The possible causes for this exception has not been included yet." + 
            "\n[OpenAi Embedding Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
