package test.java.proj_11;

import main.java.proj_11.bigdata.Messages.Responses.UploadResponse;
import main.java.proj_11.bigdata.Models.BigData;
import org.junit.Assert;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import main.java.proj_11.bigdata.Interfaces.IBigDataMTOM;
import main.java.proj_11.bigdata.Messages.Requests.DownloadRequest;
import main.java.proj_11.bigdata.Messages.Requests.QueryRequest;
import main.java.proj_11.bigdata.Messages.Requests.UploadRequest;
import main.java.proj_11.bigdata.Messages.Responses.DownloadResponse;
import main.java.proj_11.bigdata.Messages.Responses.QueryResponse;
import org.junit.Before;
import org.junit.Test;


public class AppTest 
{
    private static final Logger logger = Logger.getAnonymousLogger();
    private IBigDataMTOM proxy;

    @Before
    public void before() throws MalformedURLException{
        URL wsdl = new URL(main.java.proj_11.bigdata.Interfaces.IBigDataMTOM.URI + "?wsdl"); // //url of wsdl (language that describes functionality of a SOAP based web service)
        QName qname = new QName("http://solution.nazar/big-data/mtom", "BigDataMTOMImplService");//qualified name for xml application (soap service url, port)
        Service service = Service.create(wsdl, qname); //create service with specified url and qualified name
        this.proxy = service.getPort(IBigDataMTOM.class); //take instance of web service from port where it is located
    }


    @Test
    public void downloadFile(){
        String fileName = "fileName2";
        DownloadResponse downloadResponse = this.proxy.downloadFile(new DownloadRequest(fileName)); // download file with name "fileName2" from web service
        Assert.assertNotNull(downloadResponse); // check if it is not null
        Assert.assertEquals(downloadResponse.fileName , fileName); //compare those 2 files by name
        logger.info("File Name " + downloadResponse.fileName + " Content: " + downloadResponse.content.toString()); // info about file
    }

    @Test
    public void getFileQuery(){
        String keyWord = "keyWord"; //keyword to query
        String fileName = "fileName0"; //real file name
        QueryResponse queryResponse = this.proxy.getFileQuery(new QueryRequest(keyWord)); // query file with keyword "keyWord" from web service
        Assert.assertNotNull(queryResponse); //check if it is null
        Assert.assertEquals(queryResponse.fileNames.size(), 4); //check if size of those two files are equal
        Assert.assertEquals(queryResponse.fileNames.get(0), fileName); // check if name of those two files are equal
        // logger.info("File Name " + downloadResponse.fileName + " Content: " + downloadResponse.content.toString()); // print file info
    }

    @Test
    public void uploadFile(){
        String check = "Uploaded"; //status
        BigData newData = BigData.generate(); // generate one file
        UploadResponse uploadResponse = this.proxy.uploadFile(new UploadRequest(newData.fileName, newData.rawData , newData.keyWord)); //upload file to web service
        Assert.assertNotNull(uploadResponse); //check if uploading status is not null
        Assert.assertEquals(check, uploadResponse.response); //check if uploading status are equal, so file uploaded successfully
        //logger.info("Status of uploading: " + uploadResponse.response);
    }
}
