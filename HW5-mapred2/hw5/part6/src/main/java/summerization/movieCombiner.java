package summerization;

import java.io.IOException;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;

public class movieCombiner extends Reducer <IntWritable, SortedMapWritable, IntWritable, SortedMapWritable> {
	
	/* (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
	 */
	@Override
	protected void reduce(IntWritable key, Iterable<SortedMapWritable> values,
			Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable>.Context context)
			throws IOException, InterruptedException {
		
		SortedMapWritable newmap = new SortedMapWritable();
		
		for (SortedMapWritable val: values){
			for(Entry<WritableComparable, Writable> entry: val.entrySet()){
				LongWritable newcount = (LongWritable) newmap.get(entry.getKey());
				LongWritable one = (LongWritable) entry.getValue();
				
				if (newcount != null){
					long count = newcount.get() + one.get();
					newcount.set(count);
				}
				else{
					newmap.put(entry.getKey(), one);
				}
			}
			val.clear();
		}
		context.write(key, newmap); 
	}
}
