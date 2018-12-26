package part1;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class PollutionLevelWritable implements Writable, WritableComparable<PollutionLevelWritable> {
	
	public int low;
	public int polluted;
	public int severe;
	
	

	public PollutionLevelWritable(int low, int polluted, int severe) {
		super();
		this.low = low;
		this.polluted = polluted;
		this.severe = severe;
	}



	public PollutionLevelWritable() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @return the low
	 */
	public int getLow() {
		return low;
	}



	/**
	 * @param low the low to set
	 */
	public void setLow(int low) {
		this.low = low;
	}



	/**
	 * @return the polluted
	 */
	public int getPolluted() {
		return polluted;
	}



	/**
	 * @param polluted the polluted to set
	 */
	public void setPolluted(int polluted) {
		this.polluted = polluted;
	}



	/**
	 * @return the severe
	 */
	public int getSevere() {
		return severe;
	}



	/**
	 * @param severe the severe to set
	 */
	public void setSevere(int severe) {
		this.severe = severe;
	}



	//writable
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		low = in.readInt();
		polluted = in.readInt();
		severe = in.readInt();
	}
	
	//writable
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(low);
		out.writeInt(polluted);
		out.writeInt(severe);
	}
	
	
	//writable comparable
	public int compareTo(PollutionLevelWritable o) {
		// TODO Auto-generated method stub
		if(this.getLow()>o.getLow()) return 1;
		if(this.getLow()<o.getLow()) return -1;
		return 0;
	}
	
	@Override
	public String toString(){
		return low + "\t" + polluted + "\t" + severe;
	}
}