package part2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	public static void main(String[] args) throws Exception{
		//Path input = new Path(args[0]);
		//Path output = new Path(args[1]);
		Path input = new Path("/Users/aliceliang/CS/7250/pm2.5/data/Shenyang_out.csv");
		Path output = new Path("/Users/aliceliang/CS/7250/pm2.5/output/part2_shenyang");
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "pollution level count");
		
		job.setJarByClass(AverMapper.class);
		job.setMapperClass(AverMapper.class);
		job.setReducerClass(AverReducer.class);
		
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(DateWritable.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		job.setOutputKeyClass(DateWritable.class);
		job.setOutputValueClass(PollutionValueWritable.class);
		
		FileInputFormat.addInputPath(job, input);
		job.setInputFormatClass(TextInputFormat.class);
		
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