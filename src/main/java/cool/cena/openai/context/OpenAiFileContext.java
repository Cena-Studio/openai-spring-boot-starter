package cool.cena.openai.context;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Base64;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import cool.cena.openai.OpenAiApiAccessor;
import cool.cena.openai.exception.image.ImageFileInvalidException;
import cool.cena.openai.pojo.file.OpenAiListFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiDeleteFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiDownloadFileResponseBody;
import cool.cena.openai.pojo.file.OpenAiFileRequestBody;
import cool.cena.openai.pojo.file.OpenAiFileResponseBody;

public class OpenAiFileContext {

    private OpenAiApiAccessor apiAccessor;

    // constructor
    public OpenAiFileContext(OpenAiApiAccessor apiAccessor) {
        this.apiAccessor = apiAccessor;
    }

    public OpenAiListFileResponseBody listFiles(){
        return apiAccessor.listFiles();
    }

    public OpenAiFileResponseBody createFile(Object file, String purpose){
        Resource fileResource = this.resourcify(file);
        OpenAiFileRequestBody requestBody = new OpenAiFileRequestBody();
        requestBody.setFile(fileResource);
        requestBody.setPurpose(purpose);
        return apiAccessor.createFile(requestBody);
    }

    public OpenAiDeleteFileResponseBody deleteFile(String fileId){
        return apiAccessor.deleteFile(fileId);
    }

    public OpenAiFileResponseBody retrieveFile(String fileId){
        return apiAccessor.retrieveFile(fileId);
    }

    public OpenAiDownloadFileResponseBody downloadFile(String fileId){
        return apiAccessor.downloadFile(fileId);
    }

    private Resource resourcify(Object fileObject){
        
        if(fileObject instanceof String){

            String fileString = (String) fileObject;
            // try if the imageObject is a base64 string
            try {

                byte[] fileBytes = Base64.getDecoder().decode((String) fileObject);
                return new ByteArrayResource(fileBytes);

            // if not, it could be a local path or a url
            } catch (IllegalArgumentException e) {

                File file = new File(fileString);
                // it is a valid local path
                if (file.exists()) {
                    
                    return new FileSystemResource(file);

                // it is not a valid local path, maybe it is a url
                } else {

                    try {

                        return new UrlResource(fileString);

                    // it is not a valid image string
                    } catch (MalformedURLException f) {
                        
                        throw new ImageFileInvalidException(f.getMessage());
                    }

                }

            }

        }else if(fileObject instanceof File){

            return new FileSystemResource((File) fileObject);

        }else if(fileObject instanceof FileSystemResource){

            return (FileSystemResource) fileObject;

        }else if(fileObject instanceof UrlResource){

            return (UrlResource) fileObject;

        }else if(fileObject instanceof ByteArrayResource){

            return (ByteArrayResource) fileObject;

        }
        
        throw new ImageFileInvalidException();
    };
}
