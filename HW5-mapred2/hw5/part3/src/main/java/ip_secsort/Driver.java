package ip_secsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	public static void main(String[] args) throws Exception{
		//Path input = new Path(args[0]);
		//Path output = new Path(args[1]);

		Path input = new Path("/Users/aliceliang/CS/7250/HW5/ip");
		Path output = new Path("/Users/aliceliang/CS/7250/HW5/ip/output");
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "ipsecsort");
		
		job.setJarByClass(ipMapper.class);
		job.setMapperClass(ipMapper.class);
		job.setReducerClass(ipCombiner.class);
		job.setCombinerClass(ipCombiner.class); 
		job.setPartitionerClass(ipPartitioner.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		job.setSortComparatorClass(SecSortComparator.class);
		
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(CompositeKeyWritable.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		job.setOutputKeyClass(CompositeKeyWritable.class);
		job.setOutputValueClass(LongWritable.class);
		
		FileInputFormat.addInputPath(job, input);
		job.setInputFormatClass(TextInputFormat.class);
		
		FileOutputFormat.setOutputPath(job, output);
		
		FileSystem hdfs = FileSystem.get(conf);
		if(hdfs.exists(output)){
			hdfs.delete(output, true);
		}		
		job.waitForCompletion(true);		
	}
}