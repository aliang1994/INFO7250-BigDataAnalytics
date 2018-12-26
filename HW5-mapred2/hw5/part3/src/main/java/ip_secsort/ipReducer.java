package ip_secsort;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;


public class ipReducer extends Reducer <CompositeKeyWritable, LongWritable, CompositeKeyWritable, LongWritable> {

	//public HashMap<CompositeKeyWritable, LongWritable> newmap = new HashMap<CompositeKeyWritable, LongWritable>();
	 
	@Override
	protected void reduce(CompositeKeyWritable key, Iterable<LongWritable> values,
			Reducer<CompositeKeyWritable, LongWritable, CompositeKeyWritable, LongWritable>.Context context)
			throws IOException, InterruptedException {
		
		long max = 0;
		for (LongWritable val: values){
			if(val.get()>max){
				max = val.get();;
			}
		}
		
		
		LongWritable count = new LongWritable(max);
		
		
	    context.write(key, count);
		
	}
}
