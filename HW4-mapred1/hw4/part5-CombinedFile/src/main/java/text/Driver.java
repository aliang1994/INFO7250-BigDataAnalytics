package text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class Driver {
public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "textwordcount");
		
		job.setJarByClass(TextMapper.class);
		job.setMapperClass(TextMapper.class);
		job.setReducerClass(TextReducer.class);
		
		job.setNumReduceTasks(1);
		
		job.setInputFormatClass(TextFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//FileInputFormat.addInputPath(job, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		Path input = new Path("/Users/aliceliang/CS/7250/HW4/input1");
		Path output = new Path("/Users/aliceliang/CS/7250/HW4/input1/output");
		
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);

		job.waitForCompletion(true);
	}
}
