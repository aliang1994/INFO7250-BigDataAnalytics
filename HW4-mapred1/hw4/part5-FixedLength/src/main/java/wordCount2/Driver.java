package wordCount2;


import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		
		Configuration conf = new Configuration();
		conf.setInt(FixedLengthInputFormat.FIXED_RECORD_LENGTH, 2); 
		
	    Job job = Job.getInstance(conf, "wordcount");
		
		job.setJarByClass(wordMapper.class);
		
		job.setInputFormatClass(FixedLengthInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(wordMapper.class);
		job.setReducerClass(wordReducer.class);
		
		job.setNumReduceTasks(2);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//FileInputFormat.addInputPath(job, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		//debugging
		Path input = new Path("/Users/aliceliang/CS/7250/HW4/input4");
		Path output = new Path("/Users/aliceliang/CS/7250/HW4/input4/output");
		
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		job.waitForCompletion(true);
	}

}
