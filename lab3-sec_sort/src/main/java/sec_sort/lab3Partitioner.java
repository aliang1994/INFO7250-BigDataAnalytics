package sec_sort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class lab3Partitioner extends Partitioner<CompositeKeyWritable, NullWritable> {

	

	@Override
	public int getPartition(CompositeKeyWritable arg0, NullWritable arg1, int numReducerTasks) {
		int hash = arg0.getZipcode().hashCode();
		int partition = hash % numReducerTasks;
		return partition;
	}
}
