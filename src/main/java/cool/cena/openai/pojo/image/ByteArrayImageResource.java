package cool.cena.openai.pojo.image;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class ByteArrayImageResource implements Resourcifyable{

    private byte[] imageBytes;

    public ByteArrayImageResource(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    @Override
    public Resource toResource() {
        return new ByteArrayResource(imageBytes);
    }
    
}
