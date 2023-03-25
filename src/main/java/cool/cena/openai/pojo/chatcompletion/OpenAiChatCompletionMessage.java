package cool.cena.openai.pojo.chatcompletion;

public class OpenAiChatCompletionMessage {
    private String role, content;

    public OpenAiChatCompletionMessage(){}

    public OpenAiChatCompletionMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasSameRole(OpenAiChatCompletionMessage anotherMessage){
        return this.role == anotherMessage.getRole();
    }

    public void merge(OpenAiChatCompletionMessage anotherMessage){
        this.content += "\n" + anotherMessage.content;
    }
    
}
