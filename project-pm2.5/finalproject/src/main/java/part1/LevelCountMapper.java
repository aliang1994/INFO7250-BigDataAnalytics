package part1;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LevelCountMapper extends Mapper <LongWritable, Text, IntWritable, DoubleWritable> {
	private IntWritable outkey = new IntWritable();
	private DoubleWritable outval = new DoubleWritable();

	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		
		if (key.get() == 0 ){ // header
			return;
		}
		else{
			String values[] = value.toString().split(","); // csv
			
			try{
				String year = values[1];
				if(Integer.parseInt(year)>=2013){ // only use data after 2013
					int month = Integer.parseInt(values[2]);
					
					//beijing: 9; shanghai:7; guangzhou:8; chengdu:8; shenyang:7
					if (!values[7].equals("NA")){
						double level = Double.parseDouble(values[7]);
					    outval.set(level);
					    outkey.set(month);
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
