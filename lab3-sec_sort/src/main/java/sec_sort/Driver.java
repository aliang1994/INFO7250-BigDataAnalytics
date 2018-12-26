package sec_sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	public static void main(String[] args) throws Exception{
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "secsort");
		
		job.setJarByClass(lab3Mapper.class);
		job.setMapperClass(lab3Mapper.class);
		job.setReducerClass(lab3Reducer.class);
		job.setPartitionerClass(lab3Partitioner.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		job.setSortComparatorClass(SecSortComparator.class);
		
		job.setNumReduceTasks(1);
		
		job.setMapOutputKeyClass(CompositeKeyWritable.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		job.setOutputKeyClass(CompositeKeyWritable.class);
		job.setOutputValueClass(NullWritable.class);
		
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