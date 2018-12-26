package wordCount2;



import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;


public class wordMapper extends Mapper<LongWritable, BytesWritable, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	@Override
	protected void map(LongWritable key, BytesWritable value, Mapper<LongWritable, BytesWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String[] fields = value.toString().split(" ");
		for (String s: fields){
			word.set(s);
			context.write(word, one);
		}
	}
	

}
