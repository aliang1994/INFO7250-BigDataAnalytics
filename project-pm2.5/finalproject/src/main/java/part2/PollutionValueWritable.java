package part2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class PollutionValueWritable implements Writable, WritableComparable<PollutionValueWritable> {
	
	public double min;
	public double max;
	public double aver;
	


	public PollutionValueWritable(double min, double max, double aver) {
		super();
		this.min = min;
		this.max = max;
		this.aver = aver;
	}



	public PollutionValueWritable() {
		super();
		// TODO Auto-generated constructor stub
	}






	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}



	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}



	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}



	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}



	/**
	 * @return the aver
	 */
	public double getAver() {
		return aver;
	}



	/**
	 * @param aver the aver to set
	 */
	public void setAver(double aver) {
		this.aver = aver;
	}



	//writable
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		min = in.readDouble();
		max = in.readDouble();
		aver = in.readDouble();
	}
	
	//writable
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeDouble(min);
		out.writeDouble(max);
		out.writeDouble(aver);
	}
	
	
	//writable comparable
	public int compareTo(PollutionValueWritable o) {
		// TODO Auto-generated method stub
		if(this.getAver()>o.getAver()) return 1;
		if(this.getAver()<o.getAver()) return -1;
		return 0;
	}
	
	@Override
	public String toString(){
		return aver + "\t" + min + "\t" + max;
	}
}