package wordCount2;



import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;


public class wordMapper extends Mapper<Text, IntWritable, Text, IntWritable> {

	protected void map(Text key, IntWritable value, Mapper<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		context.write(key, new IntWritable(Integer.valueOf(value.toString())));
	}
	

}
