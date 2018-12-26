package ip_secsort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class CompositeKeyWritable implements Writable, WritableComparable <CompositeKeyWritable>{
	private String ip;
	private String date;
	
	

	public CompositeKeyWritable() {
		super();
	}


	public CompositeKeyWritable(String ip, String date) {
		super();
		this.ip = ip;
		this.date = date;
	}
	
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}


	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	public int compareTo(CompositeKeyWritable o) {
		//int result = date.compareTo(o.getDate());
		int result = ip.compareTo(o.getIp());
		
		return result;
	}

	public void readFields(DataInput in) throws IOException {
		date = in.readUTF();
		ip = in.readUTF();	
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(date);
		out.writeUTF(ip);
	}
	
	public String toString(){
		return "ip: " + ip + " \t  date: " + date;
	}
}
