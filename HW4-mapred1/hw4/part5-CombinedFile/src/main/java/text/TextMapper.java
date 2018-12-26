package text;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TextMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	//private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	private IntWritable one = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
				String[] line = value.toString().split(" ");
				for(String s: line){
					word.set(s);
					context.write(word, one);
				}
	}
}