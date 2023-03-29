package cool.cena.openai.pojo.image;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class FileImageResource implements Resourcifyable{

    private File file;

    public FileImageResource(File file) {
        this.file = file;
    }

    @Override
    public Resource toResource() {
        return new FileSystemResource(file);
    }
    
}
