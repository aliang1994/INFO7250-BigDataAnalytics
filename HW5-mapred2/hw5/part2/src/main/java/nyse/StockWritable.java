package nyse;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class StockWritable implements Writable, WritableComparable <StockWritable>{
	private String date;
	private String stock_volumn;
	private String price_adj_close;
	
	

	public StockWritable() {
		super();
	}	

	public StockWritable(String date, String stock_volumn, String price_adj_close) {
		super();
		this.date = date;
		this.stock_volumn = stock_volumn;
		this.price_adj_close = price_adj_close;
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


	/**
	 * @return the stock_volumn
	 */
	public String getStock_volumn() {
		return stock_volumn;
	}


	/**
	 * @param stock_volumn the stock_volumn to set
	 */
	public void setStock_volumn(String stock_volumn) {
		this.stock_volumn = stock_volumn;
	}


	/**
	 * @return the price_adj_close
	 */
	public String getPrice_adj_close() {
		return price_adj_close;
	}


	/**
	 * @param price_adj_close the price_adj_close to set
	 */
	public void setPrice_adj_close(String price_adj_close) {
		this.price_adj_close = price_adj_close;
	}


	public int compareTo(StockWritable o) {
		int result = price_adj_close.compareTo(o.getPrice_adj_close());
		if(result == 0){
			result = stock_volumn.compareTo(o.getStock_volumn());
		}
		return result;
	}

	public void readFields(DataInput in) throws IOException {
		date = in.readUTF();
		stock_volumn = in.readUTF();
		price_adj_close = in.readUTF();	
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(date);
		out.writeUTF(stock_volumn);
		out.writeUTF(price_adj_close);
	}
	
	public String toString(){
		return  " date: " + date  + " volumn: " + stock_volumn + " price: " + price_adj_close;
	}
}