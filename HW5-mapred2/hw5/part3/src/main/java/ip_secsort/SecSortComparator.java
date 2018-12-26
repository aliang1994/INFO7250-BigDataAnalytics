package ip_secsort;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		
		//int result = c1.getIp().compareTo(c2.getIp());
		
		DateFormat format = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
		try {
			Date d1 = format.parse(c1.getDate());
			Date d2 = format.parse(c2.getDate());
			int result = d1.compareTo(d2);
			if(result == 0){
				//return -c1.getDate().compareTo(c2.getDate());
				return -c1.getIp().compareTo(c2.getIp());
			}
			return result;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
}