package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataUtil {
	
    public void readCSVDate() throws IOException{
    	final String lineSep=System.getProperty("line.separator");
    	
    	BufferedReader in = null;
    	BufferedWriter out = null;
        try{
            // read csv file
            File inputFile = new File("/Users/aliceliang/CS/7250/pm2.5/data/ShenyangPM20100101_20151231.csv");
            File outputFile = new File("/Users/aliceliang/CS/7250/pm2.5/data/Shenyang_out.csv");
            
            in = new BufferedReader(new FileReader(inputFile));
            out =new BufferedWriter(new FileWriter(outputFile));
            
            //header
            String header = in.readLine(); 
            String new_header = header + "," + "datetime"+ lineSep;
            out.write(new_header);
            
            
            //csv body
            String line = null;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                String year = tokens[1];
                String month = tokens[2];
                String day = tokens[3];
                String hour = tokens[4];
                
                String datetime = year+"/"+month+"/"+day+":"+hour+":00:00";
             
                
                String outstr = line + "," + datetime + lineSep;
                out.write(outstr);
            }
           System.out.println("finished writing file!");
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        finally{
        	if(in!=null) in.close();
        	if(out!=null) out.close();
        }
    }
    
    public static void main(String[] args) throws Exception{
    	new DataUtil().readCSVDate();
    }
    
}
