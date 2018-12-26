package nyse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper <LongWritable, Text, IntWritable, StockAverWritable> {
	private IntWritable year = new IntWritable();
	private StockAverWritable sw = new StockAverWritable();

	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, StockAverWritable>.Context context)
			throws IOException, InterruptedException {
		
		if(key.get()==0){  // skip headers
			return;
		}
		else{
			String values[] = value.toString().split(","); // csv
			
			// String stock_symbol = null;
			String date = null;
			String price = null;
			
			try{
				
				// parse date to year
				date = values[2];
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date d = format.parse(date);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				int y = cal.get(Calendar.YEAR);
				
				//System.out.println("year is: " + y);
				year.set(y);
				
				
				// get price
				sw.setCount(1);
				price= values[8];
				sw.setAver(Float.parseFloat(price));
				
				
				// write to context
				context.write(year, sw);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
