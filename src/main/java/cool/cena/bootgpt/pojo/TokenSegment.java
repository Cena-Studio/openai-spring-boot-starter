package cool.cena.bootgpt.pojo;

public class TokenSegment {

    private int size, token;
    
    public TokenSegment(int size, int token){
        this.size = size;
        this.token = token;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    
    
}
