package homework4.ipCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ipReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
private IntWritable total = new IntWritable();
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		for (IntWritable value: values){
			sum += value.get();
		}
		total.set(sum);
		context.write(key, total);
	}
}
