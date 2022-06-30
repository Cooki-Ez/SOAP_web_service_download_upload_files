package main.java.proj_11.bigdata.Interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import main.java.proj_11.bigdata.Messages.Requests.DownloadRequest;
import main.java.proj_11.bigdata.Messages.Requests.QueryRequest;
import main.java.proj_11.bigdata.Messages.Requests.UploadRequest;
import main.java.proj_11.bigdata.Messages.Responses.DownloadResponse;
import main.java.proj_11.bigdata.Messages.Responses.QueryResponse;
import main.java.proj_11.bigdata.Messages.Responses.UploadResponse;

@MTOM// mark, that web service includes binary attachment
@WebService( //
		name = "IBigDataMTOM", //name of web service
		targetNamespace = "http://solution.nazar/big-data/mtom" //url
)
public interface IBigDataMTOM {

	public static final String URI = "http://localhost:8080/big-data/mtom";

	@WebMethod(action = "http://solution.nazar/big-data/mtom/downloadFile")//uri of method for downloading file
	public DownloadResponse downloadFile(DownloadRequest request);

	@WebMethod(action = "http://solution.nazar/big-data/mtom/uploadFile")//uri of method for uploading file
	public UploadResponse uploadFile(UploadRequest request);

	@WebMethod(action = "http://solution.nazar/big-data/mtom/getFileQuery")//uri of method for querying file
	public QueryResponse getFileQuery(QueryRequest request);
}