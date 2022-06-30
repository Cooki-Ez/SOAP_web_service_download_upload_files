package main.java.proj_11.bigdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.MTOM;

import main.java.proj_11.bigdata.Models.BigData;
import main.java.proj_11.bigdata.Interfaces.IBigDataMTOM;
import main.java.proj_11.bigdata.Messages.Requests.DownloadRequest;
import main.java.proj_11.bigdata.Messages.Requests.QueryRequest;
import main.java.proj_11.bigdata.Messages.Requests.UploadRequest;
import main.java.proj_11.bigdata.Messages.Responses.DownloadResponse;
import main.java.proj_11.bigdata.Messages.Responses.QueryResponse;
import main.java.proj_11.bigdata.Messages.Responses.UploadResponse;

@MTOM// mark, that web service includes binary attachment
@WebService( //
		name = "IBigDataMTOM", //name of web service
		targetNamespace = "http://solution.nazar/big-data/mtom" // url
)
public final class BigDataMTOMImpl implements IBigDataMTOM {


	private static List<BigData> files;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public static void main(String... args) {
		Endpoint.publish(IBigDataMTOM.URI, new BigDataMTOMImpl());
		LOGGER.info("SERVICE STARTED");	//notify that service has been started
		files = BigData.generateDataList();
	}

	@Override
	public DownloadResponse downloadFile(DownloadRequest request) {
		for ( BigData data : files)
			if(data.getFileName().equals(request.fileName))
				return new DownloadResponse(data.getFileName(), data.getContent()); //if exists file with such name download it
		return null;
	}

	@Override
	public QueryResponse getFileQuery(QueryRequest request) {
		List<String> fNames = new ArrayList<>();

		Map<String,List<BigData>> findByKeyWord  = files.stream().collect(
			Collectors.groupingBy(
			BigData::getKeyWord,
			TreeMap::new, 
			Collectors.toList()));	//find files that match given query

		List<BigData> fList = findByKeyWord.get(request.keyWord);

		for (BigData data : fList){
			fNames.add(data.fileName);
		} 
		
		return new QueryResponse(fNames);
	}

	@Override
	public UploadResponse uploadFile(UploadRequest request) {

		BigData newData = new BigData(request.content, request.fileName,  request.keyWord);

		files.add(newData);

		if (files.get(files.size()-1).fileName.equals(request.fileName) 
			&& files.get(files.size()-1).keyWord.equals(request.keyWord)
			&& files.get(files.size()-1).rawData.equals(request.content)) 
		{
			return new UploadResponse("Uploaded"); //notify about files have been uploaded successfully
		}else{
			return new UploadResponse("Failure during upload");//notify about files have NOT been uploaded
		}
	}
}