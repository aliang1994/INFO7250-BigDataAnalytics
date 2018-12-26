package summerization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class movieReducer extends Reducer <IntWritable, DoubleWritable, IntWritable, MedianStdWritable> {
	private MedianStdWritable result = new MedianStdWritable();
	
	//private ArrayList<Double> ratinglist = new ArrayList<Double>();
	
	@Override
	protected void reduce(IntWritable key, Iterable<DoubleWritable> values,
			Reducer<IntWritable, DoubleWritable, IntWritable, MedianStdWritable>.Context context)
			throws IOException, InterruptedException {
		
		ArrayList<Double> ratinglist = new ArrayList<Double>();

		
		int counter = 0;
		double sum = 0;
		
		for (DoubleWritable val: values){
			ratinglist.add(val.get());
			counter ++;
			sum += val.get();
		}
		
		//System.out.println (key + " " + counter + " " + sum);
		
		Collections.sort(ratinglist);
		
		//find median;
		double median;
		if (counter % 2 ==0) {
			median = (ratinglist.get(counter/2 - 1) + ratinglist.get(counter/2)) /2;
		}
		else{
			median = ratinglist.get(counter/2);
		}
		
		result.setMedian(String.valueOf(median));
		
		//find stdv
		double mean = sum/counter;
		double sumofsq = 0;
		for (double d: ratinglist){
			sumofsq += (d-mean)*(d-mean);
		}
		double std = Math.sqrt(sumofsq/(counter));
		result.setStdv(String.valueOf(std));
		
		
		System.out.println (key + " " + " array size: " + ratinglist.size() + " counter: " + counter + " mean: " + mean + " " + " median: " + median + " " + " std: " + std);
		context.write(key, result);		
	}
}
