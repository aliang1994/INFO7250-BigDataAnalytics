package part2;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverMapper extends Mapper <LongWritable, Text, DateWritable, DoubleWritable> {
	private DateWritable outkey;
	private DoubleWritable outval;

	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, DateWritable, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		
		if (key.get() == 0 ){ // header
			return;
		}
		else{
			String values[] = value.toString().split(","); // csv
			

			
			try{
				String year = values[1];
				if(Integer.parseInt(year)>=2013){ // only use data after 2013
					
					//beijing: 18; shanghai:17; guangzhou:17; chengdu:17; shenyang:17
					String datetime = values[17];
					String date = datetime.split(":")[0];
					
					//beijing: 9; shanghai:7; guangzhou:8; chengdu:8; shenyang:7
					if (!values[7].equals("NA")){
						double level = Double.parseDouble(values[7]);
						
					    outval = new DoubleWritable(level);
					    outkey = new DateWritable(date);
					    context.write(outkey, outval);
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
