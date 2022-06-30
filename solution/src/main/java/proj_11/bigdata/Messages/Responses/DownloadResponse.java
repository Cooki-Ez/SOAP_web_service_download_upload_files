package main.java.proj_11.bigdata.Messages.Responses;

public class DownloadResponse { //download response consists of file name and content

    public String fileName;
    public byte[] content;

    public DownloadResponse(){
    }
    
    public DownloadResponse(String fileName, byte[] content){
        this.fileName = fileName;
        this.content = content;
    }
}
