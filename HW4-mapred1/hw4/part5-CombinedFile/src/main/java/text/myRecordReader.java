package text;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class myRecordReader extends RecordReader<LongWritable, Text>{
	private LineRecordReader rec = new LineRecordReader();

	public myRecordReader(CombineFileSplit split, TaskAttemptContext context, Integer index) throws IOException {
		 FileSplit fileSplit = new FileSplit(split.getPath(index), 
                 split.getOffset(index),
                 split.getLength(index), 
                 split.getLocations());
		 rec.initialize(fileSplit, context);
	}

	@Override
	public void close() throws IOException {
		rec.close();
	}

	@Override
	public LongWritable getCurrentKey() throws IOException, InterruptedException {
		return rec.getCurrentKey();
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		return rec.getCurrentValue();
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		return rec.getProgress();
	}

	@Override
	public void initialize(InputSplit arg0, TaskAttemptContext arg1) throws IOException, InterruptedException {
		// used constructor
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		return rec.nextKeyValue();
	}
}