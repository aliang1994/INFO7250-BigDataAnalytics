package homework4.ipCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver{
    public static void main( String[] args ) throws Exception{
    	Configuration conf = new Configuration();
	    Job job = Job.getInstance(conf, "ipcount");
		
		job.setJarByClass(ipMapper.class);
		job.setMapperClass(ipMapper.class);
		job.setReducerClass(ipReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setNumReduceTasks(2);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		//debugging
		//Path input = new Path("/Users/aliceliang/CS/7250/lab2/input");
		//Path output = new Path("/Users/aliceliang/CS/7250/lab2/output");
		
		//FileInputFormat.addInputPath(job, input);
		//FileOutputFormat.setOutputPath(job, output);
		
		job.waitForCompletion(true);
    }
}
