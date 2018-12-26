package ip_secsort;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class ipCombiner extends Reducer <CompositeKeyWritable, LongWritable, CompositeKeyWritable, LongWritable> {

	 
	@Override
	protected void reduce(CompositeKeyWritable key, Iterable<LongWritable> values,
			Reducer<CompositeKeyWritable, LongWritable, CompositeKeyWritable, LongWritable>.Context context)
			throws IOException, InterruptedException {
		
		long counter = 0;
		for (LongWritable val: values){
			counter += val.get();
		}
		LongWritable count = new LongWritable(counter);
		
		
		context.write(key, count);
		
		
	}
}
