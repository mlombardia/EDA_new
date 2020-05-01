import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class DataAnalysis {
	
	public static void main(String[] args) throws IOException {

		IndexParametricWithDuplicates<IdxRecord<Double,CSVRecord>> myIndex = new IndexParametricWithDuplicates<>(IdxRecord.class);

		URL resource = DataAnalysis.class.getClassLoader().getResource("co_1980_alabama.csv");
		  
	    Reader in = new FileReader(resource.getFile());
	    Iterable<CSVRecord> records = CSVFormat.DEFAULT
			      .withFirstRecordAsHeader()
			      .parse(in);
	    
	     for (CSVRecord record : records) {
			IdxRecord<Double, CSVRecord> entry = new IdxRecord<>(Double.valueOf(record.get("daily_max_8_hour_co_concentration")),record);
			myIndex.insert(entry);
	     }
	     //myIndex.sortedPrint();
	     System.out.println(myIndex.search(new IdxRecord<Double, CSVRecord>(2.8)));
	     System.out.println(myIndex.getMax());
	     System.out.println(myIndex.getMin());
	     System.out.println(myIndex.getMin().getRow().toString());
	     IdxRecord[] range = myIndex.range(new IdxRecord<Double, CSVRecord>(3.65),new IdxRecord<Double, CSVRecord>(3.84),true,true);
	     printRange(range);
	     IdxRecord[] range_2 = myIndex.range(new IdxRecord<Double, CSVRecord>(3.65),new IdxRecord<Double, CSVRecord>(3.84),true,false);
	     printRangeInfo(range_2);
	     IdxRecord[] range_3 = myIndex.range(new IdxRecord<Double, CSVRecord>(10.5), new IdxRecord<Double, CSVRecord>(12.0),true,true);
	     printRange(range_3);
	     printRangeInfo(range_3);
	     in.close();

	}

	private static void printRange(IdxRecord[] rta){
		if(rta.length==0){
			System.out.println("empty");
			return;
		}
		System.out.print("[" + rta[0]);
		for(int i=1; i < rta.length; i++){
			System.out.print(", " + rta[i]);
		}
		System.out.println("]");
	}

	private static void printRangeInfo(IdxRecord[] rta){
		if(rta.length==0){
			System.out.println("empty");
			return;
		}
		System.out.print("[" + rta[0].getRow().toString());
		for(int i=1; i < rta.length; i++){
			System.out.print(", " + rta[i].getRow().toString());
		}
		System.out.println("]");
	}
}