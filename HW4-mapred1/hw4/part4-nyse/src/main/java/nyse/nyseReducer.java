package nyse;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class nyseReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
	
private DoubleWritable maximum = new DoubleWritable();
	
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
		
		double max = 0;
		for (DoubleWritable value: values){
			if(value.get()>max){
				max=value.get();
			}
		}
		maximum.set(max);
		context.write(key, maximum);
	}
}
