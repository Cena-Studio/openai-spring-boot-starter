package cool.cena.openai.exception.image;

import cool.cena.openai.exception.OpenAiException;

public class ImageBadRequestException extends OpenAiException {

    public ImageBadRequestException(String originalMessage){
        super(
            "\n[OpenAi Image Error] 400 BAD REQUEST. The following might be the cause:" +
            "\n[OpenAi Image Error] - The request image file must be one of RGBA (rgb with alpha channel), LA (grayscale with alpha channel), L (grayscale) formats" +
            "\n[OpenAi Image Error] - The size of request image file must be a square, i.e. height = width." +
            "\n[OpenAi Image Error] - The request is denied because the image file is not safe." +
            "\n[OpenAi Image Error] The fowllowing is the original exception message:" +
            "\n" + originalMessage
        );
    }

}
