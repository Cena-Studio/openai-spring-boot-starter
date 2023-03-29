package cool.cena.openai.pojo.image;

import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class UrlImageResource implements Resourcifyable{

    private String url;

    

    public UrlImageResource(String url) {
        this.url = url;
    }
    
    @Override
    public Resource toResource() {
        try {
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
