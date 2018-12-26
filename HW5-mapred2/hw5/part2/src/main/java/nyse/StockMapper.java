package nyse;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper <LongWritable, Text, Text, StockWritable> {

	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, StockWritable>.Context context)
			throws IOException, InterruptedException {
		
		if(key.get()==0){  // skip headers
			return;
		}
		else{
			String values[] = value.toString().split(","); // csv
			
			String stock_symbol = null;
			String date = null;
			String stock_volumn = null;
			String price_adj_close = null;
			
			try{
				stock_symbol = values[1];
				
				date = values[2];
				stock_volumn = values[7];
				price_adj_close = values[8];
				
				StockWritable st = new StockWritable(date, stock_volumn, price_adj_close);
				
				Text symbol = new Text();
				symbol.set(stock_symbol);
				
				context.write(symbol, st);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
