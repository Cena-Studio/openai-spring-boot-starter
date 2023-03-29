package cool.cena.openai.pojo.image;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class LocalPathImageResource implements Resourcifyable{

    private String localPath;

    

    public LocalPathImageResource(String localPath) {
        this.localPath = localPath;
    }



    @Override
    public Resource toResource() {
        return new FileSystemResource(localPath);
    }
    
}
