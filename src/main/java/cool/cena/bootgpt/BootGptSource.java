package cool.cena.bootgpt;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class BootGptSource {

    private BootGptApiAccessor bootGptApiAccessor;

    public BootGptSource(String httpHeaderAuthorization){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);

        this.bootGptApiAccessor = new BootGptApiAccessor(httpHeaders);

    }

    public BootGptSource(String httpHeaderAuthorization, String httpHeaderOpenAiOrganization){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", httpHeaderAuthorization);
        httpHeaders.set("OpenAI-Organization", httpHeaderOpenAiOrganization);
        
        this.bootGptApiAccessor = new BootGptApiAccessor(httpHeaders);
    }

    public BootGptContext newContext(){
        return new BootGptContext(this.bootGptApiAccessor);
    }
}
