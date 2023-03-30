package cool.cena.openai.exception.image;

import cool.cena.openai.exception.OpenAiException;

public class ImageFileInvalidException extends OpenAiException {

    public ImageFileInvalidException(){
        super(
            "\n[OpenAi Image Error] RESOURCE ERROR. The following might be the cause:" +
            "\n[OpenAi Image Error] - An unsupported type of object is input as the image file argument."
        );
    }

    public ImageFileInvalidException(String originalMessage){
        super(
            "\n[OpenAi Image Error] RESOURCE ERROR. The following might be the cause:" +
            "\n[OpenAi Image Error] - A String is used as the local path of the image file but the file does not exist." +
            "\n[OpenAi Image Error] - An invalid base64 String of the image file is used." +
            "\n[OpenAi Image Error] - A String is used as the url of a remote image file but the url format is incorrect." +
            "\n[OpenAi Image Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }

}
