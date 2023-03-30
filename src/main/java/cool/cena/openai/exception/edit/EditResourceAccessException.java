package cool.cena.openai.exception.edit;

import cool.cena.openai.exception.OpenAiException;

public class EditResourceAccessException extends OpenAiException {
    public EditResourceAccessException(String originalMessage){
        super(
            "\n[OpenAi Edit Error] RESOURCE ACCESS ERROR. The following might be the cause:" +
            "\n[OpenAi Edit Error] - Request sent but did not receive any response from the OpenAi server until the connection was reset." +
            "\n[OpenAi Edit Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }
}
