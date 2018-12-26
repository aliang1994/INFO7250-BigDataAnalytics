package sec_sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecSortComparator extends WritableComparator{

	public SecSortComparator() {
		super(CompositeKeyWritable.class, true);
	}

	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		CompositeKeyWritable c1 = (CompositeKeyWritable) a;
		CompositeKeyWritable c2 = (CompositeKeyWritable) b;
		int result = c1.getZipcode().compareTo(c2.getZipcode());
		if(result == 0){
			return -c1.getBikeid().compareTo(c2.getBikeid());
		}
		return result;
	}
}