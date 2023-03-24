package cool.cena.openai.chatcompletion.pojo.chat;

public class Message {
    private String role, content;

    public Message(){}

    public Message(String role, String content) {
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

    public boolean hasSameRole(Message anotherMessage){
        return this.role == anotherMessage.getRole();
    }

    public void merge(Message anotherMessage){
        this.content += "\n" + anotherMessage.content;
    }
    
}
