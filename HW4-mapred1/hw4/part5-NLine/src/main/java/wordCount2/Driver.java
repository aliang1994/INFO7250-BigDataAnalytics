package wordCount2;


import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class Driver {

	public static void main(String[] args) throws Exception {
		
		
		Configuration conf = new Configuration();
		conf.setInt("mapreduce.input.lineinputformat.linespermap", 500);
		
	    Job job = Job.getInstance(conf, "wordcount");
		
		job.setJarByClass(wordMapper.class);
		
		job.setInputFormatClass(NLineInputFormat.class);
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
		Path input = new Path("/Users/aliceliang/CS/7250/HW4/input3");
		Path output = new Path("/Users/aliceliang/CS/7250/HW4/input3/output");
		
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		job.waitForCompletion(true);
	}

}
