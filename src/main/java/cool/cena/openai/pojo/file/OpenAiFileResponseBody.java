package cool.cena.openai.pojo.file;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiFileResponseBody {
    private String id, object, filename, purpose;
    private Integer bytes;
    @JsonProperty("created_at")
    private Long createdAt;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public Integer getBytes() {
        return bytes;
    }
    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }
    public Long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    
}
