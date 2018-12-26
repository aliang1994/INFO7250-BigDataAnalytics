package part2;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class AverReducer extends Reducer <DateWritable, DoubleWritable, DateWritable, PollutionValueWritable> {
	private DateWritable outkey;
	private PollutionValueWritable outval;
	
	
	@Override
	protected void reduce(DateWritable key, Iterable<DoubleWritable> values,
			Reducer<DateWritable, DoubleWritable, DateWritable, PollutionValueWritable>.Context context)
			throws IOException, InterruptedException {
		
		double min = 10000;
		double max = 0;
		double aver = 0;
		
		int cnt = 0;
		double sum = 0;
		
		
		for (DoubleWritable val: values){
			double temp = val.get();
			if(temp<min) min = temp;
			if(temp>max) max = temp;
			cnt++;
			sum += val.get();
		}
		
		aver= sum/cnt;
		
		
		
		String s = String.format("%.2f", aver);
		aver = Double.parseDouble(s);
		
		outkey = new DateWritable(key.toString());
		outval = new PollutionValueWritable(min,max,aver);
		
		//System.out.println(key + " " + min + " " + max + " " + aver);
		
		context.write(outkey, outval);
		
	}
}
