package summerization;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class movieMapper extends Mapper <LongWritable, Text, IntWritable, SortedMapWritable> {
	IntWritable movieid = new IntWritable(); // key
	IntWritable rating = new IntWritable(); // value
	LongWritable one = new LongWritable(1); // count
	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, SortedMapWritable>.Context context)
			throws IOException, InterruptedException {
		
		String values[] = value.toString().split("::"); // csv
		
		try{
			movieid.set(Integer.parseInt(values[1]));
			
			rating.set(Integer.parseInt(values[2]));
			SortedMapWritable map = new SortedMapWritable();
			map.put(rating, one);
			context.write(movieid, map);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}