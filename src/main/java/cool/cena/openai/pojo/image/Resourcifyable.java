package cool.cena.openai.pojo.image;

import org.springframework.core.io.Resource;

public interface Resourcifyable {
    public Resource toResource();
}
