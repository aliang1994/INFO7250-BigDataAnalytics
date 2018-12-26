package wordCount;



import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;


public class countMapper extends Mapper<Text, Text, Text, IntWritable> {
	

	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		try{
			context.write(key, new IntWritable(Integer.valueOf(value.toString())));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}