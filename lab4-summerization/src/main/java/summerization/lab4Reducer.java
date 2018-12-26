package summerization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class lab4Reducer extends Reducer <Text, DoubleWritable, Text, MedianStdWritable> {
	MedianStdWritable result = new MedianStdWritable();
	ArrayList<Double> ratinglist = new ArrayList<Double>();
	

	
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> values,
			Reducer<Text, DoubleWritable, Text, MedianStdWritable>.Context context)
			throws IOException, InterruptedException {
		
		int counter = 0;
		double sum = 0;
		
		for (DoubleWritable val: values){
			ratinglist.add(val.get());
			counter ++;
			sum += val.get();
		}
		
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
		double std = Math.sqrt(sumofsq/(counter-1));
		result.setStdv(String.valueOf(std));
		
		context.write(key, result);
		
	}
}
