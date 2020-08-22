
/**
 * 在这里给出对类 CountriesExport 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CountriesExport {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // countryInfo(parser,"Nauru");
        // listExportersTwoProducts(parser,"fish","nuts");
        // int num = numberOfExporters (parser,"sugar");
        // System.out.println(num);
        bigExporters(parser,"$999,999,999,999");
    }
    public String countryInfo (CSVParser parser, String exportOfInterest){
        for (CSVRecord record:parser){
            String info = record.get("Country");
            if (info.equals(exportOfInterest)){
                System.out.print(record.get("Country")+":");
                System.out.print(record.get("Exports")+":");
                System.out.println(record.get("Value (dollars)")+"");
                break;
            }
        }
        return "Not Found";
    }
    
    public void listExportersTwoProducts (CSVParser parser,String a, String b){
        for (CSVRecord record:parser){
            String info = record.get("Exports");
            if (info.contains(a)&&info.contains(b)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters (CSVParser parser,String a){
        int num = 0;
        for (CSVRecord record:parser){
            String info = record.get("Exports");
            if (info.contains(a)){
                num+=1;
            }
        }
        return num;
    }
    
    public void bigExporters  (CSVParser parser, String amount){
        for (CSVRecord record:parser){
            String info = record.get("Value (dollars)");
            if (info.length()>amount.length()){
                System.out.print(record.get("Country")+" ");
                System.out.println(record.get("Value (dollars)"));
            }
        }
    }
}
