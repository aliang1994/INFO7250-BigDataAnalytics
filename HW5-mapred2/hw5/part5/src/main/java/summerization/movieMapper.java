package summerization;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class movieMapper extends Mapper <LongWritable, Text, IntWritable, DoubleWritable> {
	IntWritable movieid = new IntWritable();
	DoubleWritable rating = new DoubleWritable();

	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, DoubleWritable>.Context context)
			throws IOException, InterruptedException {

		String values[] = value.toString().split("::"); // csv
		
		try{
			movieid.set(Integer.parseInt(values[1]));
			rating.set(Double.parseDouble(values[2]));
			//System.out.println(movieid + " " + rating);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		context.write(movieid, rating);
	}
}