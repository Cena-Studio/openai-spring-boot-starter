package cool.cena.openai;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cool.cena.openai.chatcompletion.pojo.chat.OpenAiChatCompletionResponse;
import cool.cena.openai.chatcompletion.pojo.chat.OpenAiChatCompletionRequestBody;

public class OpenAiApiAccessor {

    private final String CHAT_COMPLETION_URL = "https://api.openai.com/v1/chat/completions";

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public OpenAiApiAccessor(HttpHeaders httpHeaders){
        this.restTemplate = new RestTemplate();
        this.httpHeaders = httpHeaders;
    }
    
    public OpenAiChatCompletionResponse sendChatCompletionRequest(OpenAiChatCompletionRequestBody requestBody){

        HttpEntity<OpenAiChatCompletionRequestBody> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        
        try{

            OpenAiChatCompletionResponse responseBody = this.restTemplate.postForObject(this.CHAT_COMPLETION_URL, requestEntity, OpenAiChatCompletionResponse.class);
            responseBody.setStatus(200);
            return responseBody;
        
        }catch(HttpClientErrorException e){

            OpenAiChatCompletionResponse responseBody = new OpenAiChatCompletionResponse();

            HttpStatusCode httpStatusCode = e.getStatusCode();

            if(httpStatusCode == HttpStatus.BAD_REQUEST){

                responseBody.setStatus(400);
                responseBody.setErrMessage("[BootGPT Error Prompt] 400 BAD REQUEST. The following might be the cause:\n[BootGPT Error Prompt] 1. The total tokens of the dialogue context in the current request might exceed the model's limit.");

            }else if(httpStatusCode == HttpStatus.UNAUTHORIZED){

                responseBody.setStatus(401);
                responseBody.setErrMessage("[BootGPT Error Prompt] 401 UNAUTHORIZED. The following might be the cause:\n[BootGPT Error Prompt] 1. The value of bootgpt.key attribute in the configuration file is incorrect.");

            }else{

                responseBody.setStatus(499);
                responseBody.setErrMessage("[BootGPT Error Prompt] " + httpStatusCode + ".\n[BootGPT Error Prompt] BootGPT has not yet included possible causes for this exception.");

            }

            e.printStackTrace();
            System.err.println(responseBody.getErrMessage());

            return responseBody;

        }catch(RestClientException e){

            OpenAiChatCompletionResponse responseBody = new OpenAiChatCompletionResponse();
            responseBody.setStatus(999);
            responseBody.setErrMessage("[BootGPT Error Prompt] " + e.getMessage() + ".\n[BootGPT Error Prompt] BootGPT has not yet included possible causes for this exception.");

            e.printStackTrace();
            System.err.println(responseBody.getErrMessage());

            return responseBody;

        }
    }
    
}

/* 
try {
    MyResponseObject response = restTemplate.postForObject(url, request, MyResponseObject.class);
    return response;
} catch (HttpServerErrorException e) {
    // 处理服务器返回错误的 HTTP 响应码异常
    MyResponseObject errorResponse = new MyResponseObject();
    errorResponse.setError(true);
    errorResponse.setErrorMessage("服务器返回错误的 HTTP 响应码: " + e.getStatusCode());
    return errorResponse;
} catch (HttpClientErrorException e) {
    // 处理客户端请求不正确导致的异常
    MyResponseObject errorResponse = new MyResponseObject();
    errorResponse.setError(true);
    errorResponse.setErrorMessage("客户端请求不正确: " + e.getStatusCode());
    return errorResponse;
} catch (ResourceAccessException e) {
    // 处理底层资源相关的异常，例如网络连接断开、DNS 解析失败等
    MyResponseObject errorResponse = new MyResponseObject();
    errorResponse.setError(true);
    errorResponse.setErrorMessage("底层资源异常: " + e.getMessage());
    return errorResponse;
} catch (HttpMessageNotReadableException e) {
    // 处理无法将 HTTP 响应体转换为 Java 类型的异常
    MyResponseObject errorResponse = new MyResponseObject();
    errorResponse.setError(true);
    errorResponse.setErrorMessage("无法将响应体转换为 Java 类型: " + e.getMessage());
    return errorResponse;
} catch (HttpMessageNotWritableException e) {
    // 处理无法将请求体转换为 HTTP 请求格式的异常
    MyResponseObject errorResponse = new MyResponseObject();
    errorResponse.setError(true);
    errorResponse.setErrorMessage("无法将请求体转换为 HTTP 请求格式: " + e.getMessage());
    return errorResponse;
} catch (RestClientException e) {
    // 处理 RestTemplate 中的其他异常
    MyResponseObject errorResponse = new MyResponseObject();
    errorResponse.setError(true);
    errorResponse.setErrorMessage("RestTemplate 请求异常: " + e.getMessage());
    return errorResponse;
}
 */