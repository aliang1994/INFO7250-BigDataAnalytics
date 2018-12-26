package summerization;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class lab4Mapper extends Mapper <LongWritable, Text, Text, DoubleWritable> {
	Text brand = new Text();
	DoubleWritable rating = new DoubleWritable();

	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		

		String values[] = value.toString().split(","); // csv
		
		try{
			brand.set(values[0]);
			rating.set(Double.parseDouble(values[2]));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		context.write(brand, rating);
	}
}
