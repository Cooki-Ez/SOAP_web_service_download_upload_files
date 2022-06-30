package main.java.proj_11.bigdata.Messages.Responses;

import java.util.List;

public class QueryResponse { //query response consists of file names

    public List<String> fileNames;

    public QueryResponse(){

    }

    public QueryResponse(List<String> fileNames ){
        this.fileNames = fileNames;
    }
}
