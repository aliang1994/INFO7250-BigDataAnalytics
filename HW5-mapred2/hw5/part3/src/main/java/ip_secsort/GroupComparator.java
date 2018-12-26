package ip_secsort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
	// partition, sort, group

	public GroupComparator() {
		super(CompositeKeyWritable.class, true);
	}

	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		CompositeKeyWritable c1 = (CompositeKeyWritable) a;
		CompositeKeyWritable c2 = (CompositeKeyWritable) b;
		int result = c1.getIp().compareTo(c2.getIp());
		
		return result;
	}
}
