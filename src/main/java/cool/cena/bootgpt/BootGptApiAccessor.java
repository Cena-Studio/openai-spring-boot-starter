package cool.cena.bootgpt;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import cool.cena.bootgpt.pojo.chat.Message;
import cool.cena.bootgpt.pojo.chat.RequestBody;
import cool.cena.bootgpt.pojo.chat.ResponseBody;

public class BootGptApiAccessor {

    private RestTemplate restTemplate;
    private String requestUrl;
    private HttpHeaders httpHeaders;
    private double temperature, nucleus;

    public BootGptApiAccessor(HttpHeaders httpHeaders){
        this.restTemplate = new RestTemplate();
        this.requestUrl = "https://api.openai.com/v1/chat/completions";
        this.httpHeaders = httpHeaders;
    }
    
    public ResponseBody sendRequest(List<Message> contextMessages){

        RequestBody requestBody = new RequestBody(contextMessages);
        HttpEntity<RequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        ResponseBody responseBody = this.restTemplate.postForObject(requestUrl, requestEntity, ResponseBody.class);

        return responseBody;
    }
    
}
