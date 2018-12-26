package filter;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Sink;

public class filterMapper extends Mapper <LongWritable, Text, Text, NullWritable> {
	Funnel<Person> p = new Funnel<Person>(){
		public void funnel (Person p, Sink into){
			into.putString(p.firstname, Charsets.UTF_8)
				.putString(p.lastname,Charsets.UTF_8);
		}
	};
	
	private BloomFilter<Person> friends = BloomFilter.create(p, 500, 0.1);

	public void setup(Context context) throws IOException, InterruptedException{
		Person p1 = new Person("Abby", "Lahm");
		Person p2 = new Person("Jamie", "Scott");
		
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(p1);
		list.add(p2);
		
		for(Person p: list){
			friends.put(p);
		}
	}
	
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		

		String values[] = value.toString().split(","); 
		
		try{
			Person p = new Person(values[1], values[2]);
			if(friends.mightContain(p)){
				context.write(value, NullWritable.get());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "bloom filter");
		
		job.setJarByClass(filterMapper.class);
		job.setMapperClass(filterMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(TextInputFormat.class);
		
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		FileSystem hdfs = FileSystem.get(conf);
		if(hdfs.exists(output)){
			hdfs.delete(output, true);
		}		
		
		//job.waitForCompletion(true);
		int code = job.waitForCompletion(true) ? 0 : 1;
		System.exit(code);
	}
}
