package part2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class DateWritable implements Writable, WritableComparable<DateWritable>{
	private String stringdate;
	
	public DateWritable(String stringdate) {
		this.stringdate = stringdate;
	}
	
	
	

	public DateWritable() {
		super();
		// TODO Auto-generated constructor stub
	}




	/**
	 * @return the stringdate
	 */
	public String getStringdate() {
		return stringdate;
	}


	/**
	 * @param stringdate the stringdate to set
	 */
	public void setStringdate(String stringdate) {
		this.stringdate = stringdate;
	}


	public int compareTo(DateWritable o) {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
		try {
			Date d1 = format.parse(this.stringdate);
			Date d2 = format.parse(o.getStringdate());
			int result = d1.compareTo(d2);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(stringdate);
	}

	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		stringdate = in.readUTF();
	}
	
	public String toString(){
		return this.stringdate;
	}
}
