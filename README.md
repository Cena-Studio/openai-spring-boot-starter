# Overview
## 1 Description
This project is an **unofficial** Spring Boot starter for quick access to Open AI's API developing and maintaining by Cena Studio. Aiming to facilitate Spring Boot developers, this project is licensed under [the MIT license](https://github.com/Cena-Studio/openai-spring-boot-starter/blob/main/LICENSE). In response to the characteristics of Spring Boot servers, this project has optimized the handling of concurrent requests and further encapsulated possible errors that requests may generate. In the future, this will be further enhanced according to the roadmap.

This project is currently established on Spring Boot 3.0.4. Broader version support will be provided based on feedback and demands from the community in the future. In the meantime, please make sure that your Spring Boot project version is compatible with this starter.

## 2 Development Roadmap
- Chat Completion ```<- current stage```
- Completion
- Image Completion
- HTTP Connection Configuration
- Other Completions

# Quick Start
## 1 Basic Configuration
### 1.1 Import openai-spring-boot-starter
For Maven project, please add the following dependency into your pom.xml:
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
	<version>x.x.x</version> <!-- set a specific version number -->
</dependency>
```
### 1.2 Requst Parameters Configuration
Add records to the configuration file of a Spring Boot application as needed. The following is an example of a yaml configuration file:
```yaml
openai:
    key: xx-xxxxxxxx    # *REQUIRED* your API key
    organization: xx-xxxxxxxx   # optional. your organization key
    chatCompletion:
        model: gpt-3.5-turbo # optional. the model used for the completion
        maxPromptToken: 2048 # optional. the maximum number of tokens sourced from the preceding context that can be used for a request prompt.
        maxCompletionToken: 4096 # optional. the maximum number of tokens generated by ai as a response completion.
```
As you may have noticed from the above example, it is worth mentioning that the "key" is essential and the only required field (so that OpenAI can ~~charge you for~~ offer their services).
### 1.3 Autowiring
Use auto-wiring mechanism to load the OpenAISource bean in the classes where it is needed. The following is an example for a Service class:
```java
import cool.cena.openai.OpenAiSource;

@Service
public class XxxService{

    @Autowired
    OpenAiSource openAiSource

}
```
## 3 Chat Completion


