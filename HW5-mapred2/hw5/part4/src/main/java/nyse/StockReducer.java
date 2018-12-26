package nyse;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class StockReducer extends Reducer <IntWritable, StockAverWritable, IntWritable, StockAverWritable> {

	private StockAverWritable aver = new StockAverWritable();
	
	@Override
	protected void reduce(IntWritable key, Iterable<StockAverWritable> values,
			Reducer<IntWritable, StockAverWritable, IntWritable, StockAverWritable>.Context context)
			throws IOException, InterruptedException {
		
		float sum = 0;
		float count = 0;
		
		for (StockAverWritable sw: values){
			count += sw.getCount();
			sum += sw.getAver()*sw.getCount();
		}
		aver.setAver(sum/count);
		aver.setCount(count);
		

		context.write(key, aver);
		
	}
}
