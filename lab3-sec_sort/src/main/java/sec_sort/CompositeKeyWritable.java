package sec_sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class CompositeKeyWritable implements Writable, WritableComparable <CompositeKeyWritable>{
	private String zipcode;
	private String bikeid;
	
	

	public CompositeKeyWritable() {
		super();
	}

	public CompositeKeyWritable(String zipcode, String bikeid) {
		super();
		this.zipcode = zipcode;
		this.bikeid = bikeid;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the bikeid
	 */
	public String getBikeid() {
		return bikeid;
	}

	/**
	 * @param bikeid the bikeid to set
	 */
	public void setBikeid(String bikeid) {
		this.bikeid = bikeid;
	}

	
	
	public int compareTo(CompositeKeyWritable o) {
		int result = zipcode.compareTo(o.zipcode);
		if(result == 0){
			result = bikeid.compareTo(o.bikeid);
		}
		return result;
	}

	public void readFields(DataInput in) throws IOException {
		zipcode = in.readUTF();
		bikeid = in.readUTF();	
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(zipcode);
		out.writeUTF(bikeid);
	}
	
	public String toString(){
		return zipcode + "\t" + bikeid;
	}
}
