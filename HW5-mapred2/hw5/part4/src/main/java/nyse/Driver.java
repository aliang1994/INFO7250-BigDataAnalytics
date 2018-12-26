package nyse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	public static void main(String[] args) throws Exception{
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		
		//Path input = new Path("/Users/aliceliang/CS/7250/HW5/NYSE");
		//Path output = new Path("/Users/aliceliang/CS/7250/HW5/NYSE/output_aver");
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "nyse_aver");
		
		job.setJarByClass(StockMapper.class);
		job.setMapperClass(StockMapper.class);
		job.setReducerClass(StockReducer.class);
		job.setCombinerClass(StockReducer.class);
		
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(StockAverWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(StockAverWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		
		FileInputFormat.addInputPath(job, input);
		FileOutputFormat.setOutputPath(job, output);
		
		FileSystem hdfs = FileSystem.get(conf);
		if(hdfs.exists(output)){
			hdfs.delete(output, true);
		}		
		job.waitForCompletion(true);		
	}
}