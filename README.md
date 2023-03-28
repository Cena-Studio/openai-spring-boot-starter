# Overview
## 1 Description
This project is an **unofficial** Spring Boot starter for quick access to Open AI's API being developed and maintained by **[Cena Studio](https://www.cena.cool)**. Aiming to facilitate Spring Boot developers, this project is licensed under **[the MIT license](https://github.com/Cena-Studio/openai-spring-boot-starter/blob/main/LICENSE)**. In response to the characteristics of Spring Boot servers, this project has optimized the handling of concurrent requests and further encapsulated possible errors that requests may generate. In the future, this will be further enhanced according to the roadmap.

This project is currently established on Spring Boot 3.0.4. Broader version support will be provided based on feedback and demands from the community in the future. In the meantime, please make sure that your Spring Boot project version is compatible with this starter.

## 2 Development Roadmap
![roadmap](https://github.com/Cena-Studio/openai-spring-boot-starter/blob/main/assets/roadmap.png)

# Quick Start
## 1 Basic Preparation
### 1.1 Import Package
For Maven project, please add the following dependency into your pom.xml:
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<version>x.x.x</version> <!-- set a specific version number -->
</dependency>
```
### 1.2 Configuration
Add records to the configuration file of a Spring Boot application as needed. The following is a very first yaml configuration file:
```yaml
openai:
    key: xx-xxxxxxxx    # *REQUIRED* your API key
    organization: xx-xxxxxxxx   # optional organization key. set a specific organization when you belong to several of them
```
In the configuration file, the `openai.key` is the one and only required field (so that OpenAI can ~~charge you for~~ offer their services). For advanced developers, this starter also provides more optional parameters based on the OpenAPI spec. The parameters available for each specific API will be listed in the following related sections. Or, you can refer to **[the application.yml template](https://github.com/Cena-Studio/openai-spring-boot-starter/blob/main/application.yml)** for a complete configuration example.
### 1.3 Autowiring
Use auto-wiring mechanism to load the OpenAISource bean in the classes wherever the request is needed. The following is an example for a service class:
```java
import cool.cena.openai.OpenAiSource;

@Service
public class MyService{

    @Autowired
    OpenAiSource openAiSource;

}
```
## 2 Text Completion

## 3 Chat Completion
A chat completion could be seen as a completion of a conversation context, which is also the core of ChatGPT. This starter offers a good management of the context by using a "message tree".
### 3.1 Simple Usage
Assuming the OpenAiSource has been autowired in a class and there is a "myMethod()" method inside that class, a simple way to create a chat completion based on OpenAPI spec could be seen below:
```java
public void MyMethod(){

    // create a chat completion context for requests
    OpenAiChatCompletionContext chatCompletion = openAiSource.createChatCompletionContext();
    
    // add a single prompt to the context
    chatCompletion.addUserMessage("Which is the largest lake in scotland");
    
    // request for a chat completion with the prompt
    OpenAiChatCompletionResponseBody response = chatCompletion.create();

    // GPT responds you with message "The largest lake in Scotland is actually Loch Lomond."

}
```
The `OpenAiChatCompletionResponse` instance is strictly encapsulated according to the response body structure provided by OpenAI, and the property names follow Java camel case conventions. You can use getters directly to retrieve any properties you need from the instance. For example:
```java
// retrieve the message content "The largest lake in Scotland is actually Loch Lomond"
String messageContent = response.getChoices().get(0).getMessage().getContent();
// retrieve the token consumed by the prompt
int promptTokens = response.getUsage().getPromptTokens();
```
However, retrieving the message content using the above approach might be tedious. Alternatively, you may use a shortcut method provided by this starter:
```java
// retrieve the message content "The largest lake in Scotland is actually Loch Lomond" using a shortcut
String message = response.getMessage();
```
If we want to follow up on the topic and ask "what about the highest mountain". According to the context, GPT should respond with the highest mountain in Scotland instead of the highest one in the whole world. To manually realize such feature, we may use the following code (soon you will learn in Section 3.2 that there are much better ways to achieve this):
```java
// create a chat completion context for requests
OpenAiChatCompletionContext chatCompletion = openAiSource.createChatCompletionContext();

// add a single prompt to the context
chatCompletion.addUserMessage("Which is the largest lake in scotland")
    .addAssistantMessage("The largest lake in Scotland is actually Loch Lomond")
    .addUserMessage("What about the highest mountain?");

// request for a chat completion with the prompt
OpenAiChatCompletionResponseBody response = chatCompletion.create();

// GPT responds you with message "The highest mountain in Scotland is Ben Nevis."
```

### 3.2 Ongoing Context
Though the above example works, it looks stupid since everytime a complete prompt context has to be reconstructed from the beginning. Don't worry, the powerful support of this starter is just beginning to be shown to you.

This time let's make the same two requests about lakes and mountains again, but with another coding:
```java
OpenAiChatCompletionContext chatCompletion = openAiSource.createChatCompletionContext();

OpenAiChatCompletionResponseBody response = chatCompletion.create("Which is the largest lake in scotland");
System.out.println(response.getMessage());  // "The largest lake in Scotland is actually Loch Lomond."

response = chatCompletion.create("What about the highest mountain?");
System.out.println(response.getMessage());  // "The highest mountain in Scotland is Ben Nevis."
```
As can be seen, for each conversation (or chat room, in practical terms), only keeping a single `OpenAiChatCompletionContext` instance is needed. The context will help you manage the prompts of the conversation and even control the number of tokens consumed by the prompts during each request to prevent from the error from the OpenAi server that exceeding the max token limit.
### 3.3 Concurrent Requests
In practical, the user may send multiple messages in succession before receiving any response. OpenAI's pattern in its own ChatGPT is to prevent sending the next message before the previous one stops receiving a response. This requires more code in both frontend and Spring Boot application to block the user's messages, and if one response gets stuck, the conversation might be unable to proceed.

Let's see the following example and understand how this starter handle the relevant issue:
```java
@Service
public class MyService{

    Map<Integer, OpenAiChatCompletionContext> contextMap;  // just an example, assuming contexts of several conversations have been stored in a map

    public void MyMethod(int conversationId, String userMessage){
        
        OpenAiChatCompletionContext chatCompletion = contextMap.get(conversationId);
        // user send "Which is the largest lake in scotland" and triggered request_1
        // user send "What about the highest mountain?" and triggered request_2
        OpenAiChatCompletionResponseBody response = chatCompletion.create(userMessage);

        System.out.println(response.getMessage());  // "The largest lake in Scotland is Loch Lomond and the highest mountain in Scotland is Ben Nevis."

    }

}
```
With the above implementation, given that the user send "largest lake in scotland" and "the highest mountain" in succession so there will be two requests waiting for responses simultineously. Then, when the first response is received, it will be deprecated since it is outdated and an exception will be thrown. The second response, generated based on both the two user prompts, will give a complete reply.

Having this mechanism, other exceptions such as the HTTP exceptions caused by the network connection are also well solved. There is no need to use additional code in the project to prevent a series of subsequent problems caused by a single request response failure. However, if there is necessary logic that must be executed after the request, then the try-catch block can be used to catch the exception. To this end, the starter has also encapsulated the possible exceptions. For details please refer to **[the exception documentation](https://github.com/Cena-Studio/openai-spring-boot-starter/blob/main/exception.md)**.
### 3.3 Request Parameters
A complete general configuration for initializing every chat completion context can be written in the configuration file as shown below:
```yaml
openai:
    key: xx-xxxxxxxx
    chatCompletion:
        model: gpt-3.5-turbo    # the model used for the completion
        temperature: 1  # 0~2 decimal. degree of randomness in the output. more randomness with bigger value
        topP: 1 # another parameter controlling the randomness. OpenAi suggests not setting both temperature and topP simultaneously
        n: 1    # Number of candidate completions generated for a same prompt request
        stream: false   # *YET_NOT_SUPPORTED* if the completion responds as a Server-Sent-Event stream
        stop: ["bye", "ok"] # a list of at most 4 sequences where the API will stop generating further tokens.
        maxPromptToken: 3000    # the maximum number of tokens sourced from the preceding context that can be used for a request prompt.
        maxCompletionToken: 4096    # the maximum number of tokens generated by ai as a response completion.
        presencePenalty: 0 # -2~2 decemal. official explanation: https://platform.openai.com/docs/api-reference/parameter-details
        frequencyPenalty: 0 # -2~2 decemal. official explanation: https://platform.openai.com/docs/api-reference/parameter-details
        logitBias: # -100~100 decimal. the likelihood of each specific token appears in the completion
            1965: 1.0   # the logit bias of token 1964 is 1.0.
            2023: -0.5  #  token 2023 is more unlikely to appear in the completion since its logit bias is negative.
        user: cena  # the user of the completion request
```
When using the service, the configuration of each single context instance can be dynamically customed whenever needed. An example is given below:
```java
@Service
public class MyService{

    @Autowired
    OpenAiSource openAiSource;

    public void MyMethod(int conversationId, String userMessage){
        
        OpenAiChatCompletionContext chatCompletionOne = openAiSource.createChatCompletionContext();
        OpenAiChatCompletionContext chatCompletionTwo = openAiSource.createChatCompletionContext();

        // chatCompletionOne and ...Two have same configurations when making requests

        chatCompletionOne.setModel("gpt-4");
        chatCompletionTwo.setTemperature("1.3");
        
        // now chatCompletionOne and ...Two have different configurations

    }

}
```
### 3.4 Choices
All the examples discussed so far are based on the default choices, i.e. n = 1. So, can this starter handle multiple choices? The answer is absolutely yes.
Let's make a request first:
```java
// create a context, set the request parameter n = 3 and make a request
OpenAiChatCompletionContext chatCompletion = openAiSource.createChatCompletionContext();
OpenAiChatCompletionResponseBody response = chatCompletion.setN(3).create("Is there a monster in Loch Ness?");
```
Now the response conains three choices:
```java
String messageContentOne = response.getChoices().get(0).getMessage().getContent();
String messageContentTwo = response.getChoices().get(1).getMessage().getContent();
String messageContentThree = response.getChoices().get(2).getMessage().getContent();

System.out.println(messageContentOne);  // "I don’t have personal beliefs..."
System.out.println(messageContentTwo);  // "I cannot prove or disprove..."
System.out.println(messageContentThree);    // "I cannot assert the existence..."
```
Or, fetch them by using the shorcut:
```java
String messageContentOne = response.getMessage();   // same as response.getMessage(0)
String messageContentTwo = response.getMessage(1);
String messageContentThree = response.getMessage(2);
```
Now there are three branches in the context. To continue with one of the branches:
```java
// switch the context to the second choice (index start from 0) and continue the conversation based on it
response = chatCompletion.setN(2).switchVersion(1).create("Got it. Thank you, GPT.");

//now you get two choices responding to the above request

// keep requesting without switch version
response = chatCompletion.create("Tell me a joke");
```
As demonstrated above, managing multiple choices is super concise with the strong support of this starter. But there could be two questions remained:
1. What if I don't switch version after getting a multiple choices response?
2. Supposing we've made several multiple-choices requests, each time we switch a choice and make the next request so the conversation goes further and further. What if we want to roll back to the 3-choices response for the very first request and choose another branch of it and extend another conversation?

For Question 1, if the context is not switched manually, it will by default adopts the first choice. Therefore, the last statement in the above code example equals to the following statement:
```java
response = chatCompletion.switchVersion(0).create("Tell me a joke");
```
For Question 2, even there is such a complicated requirement, this starter still support it by using the following implementation:
```java
// create a context
OpenAiChatCompletionContext chatCompletion = openAiSource.createChatCompletionContext();
// make the 1st request for a 3-choices response
OpenAiChatCompletionResponseBody response = chatCompletion.setN(3).create("Is there a monster in Loch Ness?");

// get the needed information from the response

// switch the context to the third choice and record that version
Version savedVersion = chatCompletion.switchVersion(2).getVersion();
// switch back to the second choice and make the 2nd request for a 2-choices response
response = chatCompletion.setN(2).switchVersion(1).create("Got it. Thank you, GPT.");

// get the needed information from the response

// the conversation goes further with the 3rd request
response = chatCompletion.create("Tell me a joke");

// get the needed information from the response

// roll back to the very early version
chatCompletionOne.switchVersion(savedVersion);
```
In one word, the only thing needed to be done for switching to an early version is to use the getVersion() method to take a snapshot of it.

Now we are almost at the end of the Chat Completion. But before that, there is a final important note about `switchVersion()`. Please see the final example below:
```java
// supposing the n value is set to 3 in the configuration file
// make the 1st request for a 3-choices response
OpenAiChatCompletionResponseBody response = chatCompletion.create("Is there a monster in Loch Ness?");

// save a early version
Version earlyVersion = chatCompletion.getVersion();


// make several requests
response = chatCompletion.create("Got it. Thank you, GPT.");
response = chatCompletion.create("Tell me a joke");
...
...

// save the latest version
Version latestVersion = chatCompletion.getVersion();

// there is no problem to roll back to the early version
chatCompletionOne.switchVersion(earlyVersion);

// ERROR because the latestVersion no longer exists
chatCompletionOne.switchVersion(latestVersion);
```
**ATTENTION** When rolling back to an early version, the child branches will be discarded to keep the context safe. Developers should be careful when switch to a version that is not the latest version of a branch.