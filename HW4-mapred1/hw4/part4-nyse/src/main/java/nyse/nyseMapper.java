package nyse;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class nyseMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{
	//private final static IntWritable one = new IntWritable(1);
	private Text stocksymbol = new Text();
	private DoubleWritable price = new DoubleWritable();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		try{
			if(key.get()==0){  // skip headers
				return;
			}
			else{
				String[] line = value.toString().split(",");
				
				stocksymbol.set(line[1]);
				price.set(Double.parseDouble(line[4]));
				context.write(stocksymbol, price);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}