package sec_sort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class lab3Mapper extends Mapper <Object, Text, CompositeKeyWritable, NullWritable> {

	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, CompositeKeyWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {

		String values[] = value.toString().split(","); // csv
		String zipcode = null;
		String bikeid = null;
		
		try{
			zipcode = values[10];
			bikeid = values[8];
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(zipcode != null && bikeid != null){
			CompositeKeyWritable c = new CompositeKeyWritable(zipcode, bikeid);
			try{
				context.write(c, NullWritable.get()); // nullwritable is static
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
