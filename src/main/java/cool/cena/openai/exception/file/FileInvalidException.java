package cool.cena.openai.exception.file;

import cool.cena.openai.exception.OpenAiException;

public class FileInvalidException extends OpenAiException {

    public FileInvalidException(){
        super(
            "\n[OpenAi File Error] RESOURCE ERROR. The following might be the cause:" +
            "\n[OpenAi File Error] - An unsupported type of object is input as the image file argument."
        );
    }

    public FileInvalidException(String originalMessage){
        super(
            "\n[OpenAi File Error] RESOURCE ERROR. The following might be the cause:" +
            "\n[OpenAi File Error] - A String is used as the local path of the image file but the file does not exist." +
            "\n[OpenAi File Error] - An invalid base64 String of the image file is used." +
            "\n[OpenAi File Error] - A String is used as the url of a remote image file but the url format is incorrect." +
            "\n[OpenAi File Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }

}
