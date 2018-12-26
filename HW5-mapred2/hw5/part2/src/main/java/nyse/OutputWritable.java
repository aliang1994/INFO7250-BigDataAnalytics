package nyse;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OutputWritable {
	private String datemax;
	private String datemin;
	private String pricemax;
	
	

	public OutputWritable() {
		super();
	}		


	public OutputWritable(String datemax, String datemin, String pricemax) {
		super();
		this.datemax = datemax;
		this.datemin = datemin;
		this.pricemax = pricemax;
	}


	/**
	 * @return the datemax
	 */
	public String getDatemax() {
		return datemax;
	}


	/**
	 * @param datemax the datemax to set
	 */
	public void setDatemax(String datemax) {
		this.datemax = datemax;
	}


	/**
	 * @return the datemin
	 */
	public String getDatemin() {
		return datemin;
	}


	/**
	 * @param datemin the datemin to set
	 */
	public void setDatemin(String datemin) {
		this.datemin = datemin;
	}


	/**
	 * @return the pricemax
	 */
	public String getPricemax() {
		return pricemax;
	}


	/**
	 * @param pricemax the pricemax to set
	 */
	public void setPricemax(String pricemax) {
		this.pricemax = pricemax;
	}


	public int compareTo(OutputWritable o) {
		int result = datemax.compareTo(o.getDatemax());
		if(result == 0){
			result = datemin.compareTo(o.getDatemin());
		}
		return result;
	}

	public void readFields(DataInput in) throws IOException {
		datemax = in.readUTF();
		datemin = in.readUTF();
		pricemax = in.readUTF();	
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(datemax);
		out.writeUTF(datemin);
		out.writeUTF(pricemax);
	}
	
	public String toString(){
		return  " date max_volumn: " + datemax  + " date min_volumn: " + datemin + " price: " + pricemax;
	}
}