package summerization;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;


public class movieReducer extends Reducer <IntWritable, SortedMapWritable, IntWritable, MedianStdWritable> {
	private MedianStdWritable result = new MedianStdWritable();
	private TreeMap<Integer, Long> ratingtree = new TreeMap<Integer, Long>();

		
	@Override
	protected void reduce(IntWritable key, Iterable<SortedMapWritable> values,
			Reducer<IntWritable, SortedMapWritable, IntWritable, MedianStdWritable>.Context context)
			throws IOException, InterruptedException {
		
		
		int counter = 0;
		long sum = 0;
		ratingtree.clear();
		
		for (SortedMapWritable val: values){
			for(Entry<WritableComparable, Writable> entry: val.entrySet()){			
				int rating = ((IntWritable) entry.getKey()).get();
				long count = ((LongWritable) entry.getValue()).get();
				
				counter += count;
				sum += rating*count;
				
				Long currcount = ratingtree.get(rating);
				if(currcount == null){
					ratingtree.put(rating, count);
				}
				else{
					ratingtree.put(rating, count+currcount);
				}
				
			}
		}
			
		
		//find median;
		double median = 0;
		
		int totalcount = 0;
		for(Entry<Integer, Long> entry: ratingtree.entrySet()){
			if(counter / 2 < totalcount) {
				median = entry.getKey()-1;
				break;
			}	
			totalcount += entry.getValue();
		}
		
		result.setMedian(String.valueOf(median));
		
		//find stdv
		
		double std = 0;
		double mean = sum/counter;
		double sumofsq = 0;
		for (Entry<Integer, Long> entry: ratingtree.entrySet()){
			sumofsq += (entry.getKey()-mean) * (entry.getKey()-mean) * entry.getValue();
		}
		std = Math.sqrt(sumofsq/(counter));
		result.setStdv(String.valueOf(std));
		
		
		//System.out.println (key + " " + " sum: " + sum + " counter: " + counter + " mean: " + mean + " " + " median: " + median + " " + " std: " + std);
		context.write(key, result);		
		
	}
}
