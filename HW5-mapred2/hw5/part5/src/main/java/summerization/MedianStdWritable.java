package summerization;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class MedianStdWritable implements Writable, WritableComparable<MedianStdWritable> {
	
	public String median;
	public String stdv;
	
	
	
	public MedianStdWritable(String median, String stdv) {
		super();
		this.median = median;
		this.stdv = stdv;
	}
	
	

	public MedianStdWritable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @return the median
	 */
	public String getMedian() {
		return median;
	}



	/**
	 * @param median the median to set
	 */
	public void setMedian(String median) {
		this.median = median;
	}



	/**
	 * @return the stdv
	 */
	public String getStdv() {
		return stdv;
	}



	/**
	 * @param stdv the stdv to set
	 */
	public void setStdv(String stdv) {
		this.stdv = stdv;
	}



	//writable
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		median = in.readUTF();
		stdv = in.readUTF();
	}
	
	//writable
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(median);
		out.writeUTF(stdv);
	}
	
	
	//writable comparable
	public int compareTo(MedianStdWritable o) {
		// TODO Auto-generated method stub
		int result = median.compareTo(o.getMedian());
		if(result==0) result = stdv.compareTo(o.getStdv());
		return result;
	}
	
	@Override
	public String toString (){
		return "median: "+ median + "\t" + " std: " + stdv ;
	}
}
