package ip_secsort;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ipMapper extends Mapper <Object, Text, CompositeKeyWritable, LongWritable> {

	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, CompositeKeyWritable, LongWritable>.Context context)
			throws IOException, InterruptedException {

		String values[] = value.toString().split(" |\\[+"); 
		String ip = null;
		String date = null;
		
	
		try{
			ip = values[0];
			String fulldate = values[4];
			date = fulldate.split(":")[0];
			//DateFormat format = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss", Locale.ENGLISH);
			//Date d = format.parse(fulldate);
			//date = d.toLocaleString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//System.out.println(ip + "date" + date);
		
		if(date != null && ip != null){
			CompositeKeyWritable c = new CompositeKeyWritable(ip, date);
			LongWritable one = new LongWritable(1);
			try{
				context.write(c, one); // nullwritable is static
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}