package cool.cena.openai.pojo.chatcompletion;

public class ChatCompletionMessage {
    private String role, content;

    public ChatCompletionMessage(){}

    public ChatCompletionMessage(String role, String content) {
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

    public boolean hasSameRole(ChatCompletionMessage anotherMessage){
        return this.role == anotherMessage.getRole();
    }

    public void merge(ChatCompletionMessage anotherMessage){
        this.content += "\n" + anotherMessage.content;
    }
    
}
