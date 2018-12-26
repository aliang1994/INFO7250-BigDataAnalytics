package wordCount2;


import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

public class SeqFileWriter {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Configuration config = new Configuration();
        Path input = new Path("/Users/aliceliang/CS/7250/HW4/input5");
        Path seqpath = new Path(input + "/seqfile.seq");
        
        SequenceFile.Writer writer = SequenceFile.createWriter(config,
                SequenceFile.Writer.file(seqpath), SequenceFile.Writer.keyClass(Text.class),
                SequenceFile.Writer.valueClass(IntWritable.class));
      
        writer.append(new Text("key1"), new IntWritable(8));
        writer.append(new Text("key2"), new IntWritable(2));
        writer.append(new Text("key1"), new IntWritable(5));
        writer.append(new Text("key2"), new IntWritable(10));
        writer.append(new Text("key3"), new IntWritable(120));
        writer.append(new Text("key4"), new IntWritable(10));

        writer.close();
    }
}