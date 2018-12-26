package topk;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class topkReducer extends Reducer <NullWritable, Text, NullWritable, Text> {
	private TreeMap<Integer, Text> scoremap;
	

	
	@Override
	protected void cleanup(Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		for(Text t: scoremap.values()){
			context.write(NullWritable.get(), t);
		}
	}


	@Override
	protected void setup(Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		this.scoremap = new TreeMap<Integer, Text>();	}


	@Override
	protected void reduce(NullWritable key, Iterable<Text> values,
			Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		for(Text t: values){
			String v[] = t.toString().split(",");
			int score = Integer.parseInt(v[1]);
			scoremap.put(score, new Text(t));
			
			if(scoremap.size()>10){
				scoremap.remove(scoremap.firstKey());
			}
		}
	}
}
