package nyse;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class StockAverWritable implements Writable {
	private float aver;
	private float count;
	
	

	public StockAverWritable() {
		super();
	}	


	public StockAverWritable(float aver, float count) {
		super();
		this.aver = aver;
		this.count = count;
	}
	


	/**
	 * @return the aver
	 */
	public float getAver() {
		return aver;
	}


	/**
	 * @param aver the aver to set
	 */
	public void setAver(float aver) {
		this.aver = aver;
	}


	/**
	 * @return the count
	 */
	public float getCount() {
		return count;
	}


	/**
	 * @param count the count to set
	 */
	public void setCount(float count) {
		this.count = count;
	}


	
	public void readFields(DataInput in) throws IOException {
		aver = in.readFloat();
		count = in.readFloat();
	}

	public void write(DataOutput out) throws IOException {
		out.writeFloat(aver); 
		out.writeFloat(count);
	}
	
	public String toString(){
		return " average: " + aver + " count: "  + count;
	}
}