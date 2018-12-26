package topk;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class topkMapper extends Mapper <LongWritable, Text, NullWritable, Text> {
	private TreeMap<Integer,Text> scoremap;

	
	
	@Override
	protected void cleanup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		for(Text t: scoremap.values()){
			context.write(NullWritable.get(), t);
			
		}
	}


	
	@Override
	protected void setup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		this.scoremap = new TreeMap<Integer, Text>();
	}


	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		

		String values[] = value.toString().split(","); // csv
		
		try{
			int score = Integer.parseInt(values[1]);
			scoremap.put(score, new Text(value));
			
		}
		catch(Exception e){
			if(scoremap.size()>10){
				scoremap.remove(scoremap.firstKey());
			}
		}
	}
}
