package main.java.proj_11.bigdata.Messages.Requests;

public class QueryRequest { //query request only needs keyword that describes file to be completed

    public String keyWord;

    public QueryRequest(){

    }
    public QueryRequest(String keyWord){
        this.keyWord = keyWord;
    }
}
