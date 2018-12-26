package sec_sort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class lab3Reducer extends Reducer <CompositeKeyWritable, NullWritable, CompositeKeyWritable, NullWritable> {

	
	@Override
	protected void reduce(CompositeKeyWritable key, Iterable<NullWritable> values,
			Reducer<CompositeKeyWritable, NullWritable, CompositeKeyWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		for (NullWritable val: values){
			context.write(key, val);
		}
	}
}
