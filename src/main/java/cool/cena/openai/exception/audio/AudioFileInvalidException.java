package cool.cena.openai.exception.audio;

import cool.cena.openai.exception.OpenAiException;

public class AudioFileInvalidException extends OpenAiException {

    public AudioFileInvalidException(){
        super(
            "\n[OpenAi Audio Error] RESOURCE ERROR. The following might be the cause:" +
            "\n[OpenAi Audio Error] - An unsupported type of object is input as the audio file argument."
        );
    }

    public AudioFileInvalidException(String originalMessage){
        super(
            "\n[OpenAi Audio Error] RESOURCE ERROR. The following might be the cause:" +
            "\n[OpenAi Audio Error] - A String is used as the local path of the audio file but the file does not exist." +
            "\n[OpenAi Audio Error] - An invalid base64 String of the audio file is used." +
            "\n[OpenAi Audio Error] - A String is used as the url of a remote audio file but the url format is incorrect." +
            "\n[OpenAi Audio Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }

}
