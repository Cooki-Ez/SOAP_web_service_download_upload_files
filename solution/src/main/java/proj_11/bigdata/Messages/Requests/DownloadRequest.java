package main.java.proj_11.bigdata.Messages.Requests;

public class DownloadRequest { //download request only needs file of name to be completed
    public String fileName;

    public DownloadRequest(){

    }
    public DownloadRequest(String fileName){
        this.fileName = fileName;
    }
}
