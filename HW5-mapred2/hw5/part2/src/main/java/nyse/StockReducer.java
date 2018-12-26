package nyse;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class StockReducer extends Reducer <Text, StockWritable, Text, OutputWritable> {

	
	@Override
	protected void reduce(Text key, Iterable<StockWritable> values,
			Reducer<Text, StockWritable, Text, OutputWritable>.Context context)
			throws IOException, InterruptedException {
		try{
			
		
		long maxvolumn = 0;
		long minvolumn = Long.parseLong(values.iterator().next().getStock_volumn());
		double maxprice = 0;
		
		StockWritable max_volumn = null;
		StockWritable min_volumn = null;
		StockWritable max_price = null;
		
		
		for (StockWritable val: values){
		    long tempvolumn = Long.parseLong(val.getStock_volumn());
		    if (tempvolumn > maxvolumn){
		    	maxvolumn = tempvolumn;
		    	max_volumn = new StockWritable(val.getDate(), val.getStock_volumn(), val.getPrice_adj_close());
		    }
		    if (tempvolumn < minvolumn){
		    	minvolumn = tempvolumn;
		    	min_volumn = new StockWritable(val.getDate(), val.getStock_volumn(), val.getPrice_adj_close());
		    }
		    double tempprice = Double.parseDouble(val.getPrice_adj_close());
		    if (tempprice > maxprice){
		    	maxprice = tempprice;
		    	max_price = new StockWritable(val.getDate(), val.getStock_volumn(), val.getPrice_adj_close());
		    }
		}
		System.out.println(maxvolumn + " " + max_volumn);
		System.out.println(minvolumn + " " + min_volumn);
		System.out.println(maxprice + " " + max_price);

		OutputWritable ow = new OutputWritable();
		
		ow.setDatemax(max_volumn.getDate());
		ow.setDatemin(min_volumn.getDate());
		ow.setPricemax(max_price.getPrice_adj_close());

		context.write(key, ow);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
