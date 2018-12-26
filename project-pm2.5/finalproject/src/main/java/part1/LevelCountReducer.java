package part1;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;


public class LevelCountReducer extends Reducer <IntWritable, DoubleWritable, IntWritable, PollutionLevelWritable> {
	
	
	
	@Override
	protected void reduce(IntWritable key, Iterable<DoubleWritable> values,
			Reducer<IntWritable, DoubleWritable, IntWritable, PollutionLevelWritable>.Context context)
			throws IOException, InterruptedException {
		
		int cnt_low = 0;
		int cnt_medium = 0;
		int cnt_severe = 0;
		
		for (DoubleWritable val: values){
			if(val.get()<=35) cnt_low++;
			if(val.get()>35 && val.get()<150) cnt_medium++;
			if(val.get()>=150) cnt_severe++;
		}
		PollutionLevelWritable pw = new PollutionLevelWritable(cnt_low,cnt_medium,cnt_severe);
		
		
		context.write(key, pw);
		
	}
}
