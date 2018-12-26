package part3;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class topkReducer extends Reducer <NullWritable, Text, NullWritable, Text> {
	private TreeMap<Double, Text> valuemap;
	
	@Override
	protected void cleanup(Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		for(Text t: valuemap.values()){
			context.write(NullWritable.get(), t);
		}
	}


	@Override
	protected void setup(Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		this.valuemap = new TreeMap<Double, Text>();	}


	@Override
	protected void reduce(NullWritable key, Iterable<Text> values,
			Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		for(Text t: values){
			String v[] = t.toString().split(",");
			double val = Double.parseDouble(v[2]);
			valuemap.put(val, new Text(t));
			System.out.println(t);
			
			if(valuemap.size()>20){
				valuemap.remove(valuemap.firstKey());
			}
		}
	}
}
