package part3;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class topkMapper extends Mapper <LongWritable, Text, NullWritable, Text> {
	private TreeMap<Double,Text> valuemap;

	
	
	@Override
	protected void cleanup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		for(Text t: valuemap.values()){
			context.write(NullWritable.get(), t);
			
		}
	}


	
	@Override
	protected void setup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		this.valuemap = new TreeMap<Double, Text>();
	}


	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		

		String values[] = value.toString().split(","); 
		
		try{
			double pmvalue = Double.parseDouble(values[2]);
			valuemap.put(pmvalue, new Text(value));
			
			
		}
		catch(Exception e){
			// get top 20 pm2.5 values
			if(valuemap.size()>20){
				valuemap.remove(valuemap.firstKey());
			}
		}
	}
}
