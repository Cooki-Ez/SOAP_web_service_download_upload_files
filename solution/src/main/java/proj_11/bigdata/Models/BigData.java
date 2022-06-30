package main.java.proj_11.bigdata.Models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType( //map this class to a xml schema type
		namespace = "http://solution.nazar/big-data" // uri
)
public final class BigData {

	private static SecureRandom _random;
	private static final int DEFAULT_RAW_DATA_SIZE = 0x10000; // 64kB

	static {
		try {
			_random = SecureRandom.getInstanceStrong();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static List<BigData> generateDataList() {
		List<BigData> files = new ArrayList<>();
		for (int i = 0 ; i < 4 ; i++){
			String fileName = "fileName" + i;
			String keyWord = "keyWord";
			byte[] data = generateRawData(DEFAULT_RAW_DATA_SIZE); //generate random data of specified size
			files.add(new BigData(data, fileName,keyWord)); //add file to list
		}
		return files;
	}

	public static BigData generate(){ //generate test file
		String fileName = "fileNameTest";
		String keyWord  = "keyWord";
		byte[] data = generateRawData(DEFAULT_RAW_DATA_SIZE);
		return new BigData(data , fileName , keyWord);
	}

	private static byte[] generateRawData(int size) {//generate random data of specified size
		byte[] data = new byte[size];
		_random.nextBytes(data);
		return data;
	}

	public String fileName;
	public byte[] rawData;
	public String keyWord; 

	public BigData() {
	}

	public BigData(byte[] rawData, String fileName, String keyWord) {
		this.fileName = fileName;
		this.rawData = rawData;
		this.keyWord = keyWord;
	}

	public String getFileName(){
		return this.fileName;
	}
	public byte[] getContent(){
		return this.rawData;
	}
	public String getKeyWord(){
		return this.keyWord;
	}
}