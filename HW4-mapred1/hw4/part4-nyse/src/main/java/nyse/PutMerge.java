package nyse;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class PutMerge {
	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(conf);
		FileSystem local = FileSystem.getLocal(conf);

		Path localpath = new Path(args[0]); 
		Path hdfspath = new Path(args[1]); 

		try {
			FileStatus[] inputfiles = local.listStatus(localpath); // Get list of local Files
			FSDataOutputStream output = hdfs.create(hdfspath);	 // Create HDFS output stream
		
			for(int i=0;i < inputfiles.length; i++) {
				String filename = inputfiles[i].getPath().getName();
				System.out.println(filename);
				if(filename.contains("NYSE_daily_prices")){
					FSDataInputStream input = local.open(inputfiles[i].getPath());
					byte buffer[] = new byte[256]; // Open local input stream
					int bytesRead = 0;
					while ((bytesRead = input.read(buffer)) > 0 ){
						output.write(buffer,0,bytesRead);
					}
					input.close();
				}			
			}
			output.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}