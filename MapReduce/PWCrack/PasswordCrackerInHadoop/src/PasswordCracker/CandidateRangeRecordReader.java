package PasswordCracker;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class CandidateRangeRecordReader extends RecordReader<Text, Text> {
    private String rangeBegin;
    private String rangeEnd;
    private boolean done = false;

    CandidateRangeRecordReader() {

    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return new Text(rangeBegin);
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return new Text(rangeEnd);
    }

    // After creating this class, It is called with a inputSplit as a parameter. and It divides inputSplit by a record of key/value.
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        /** COMPLETE **/
        CandidateRangeInputSplit candidateRangeSplit = (CandidateRangeInputSplit) split;
        String inputRange = candidateRangeSplit.getInputRange();

        String[] inputRangeSplits = inputRange.split("/");
        rangeBegin = inputRangeSplits[0];
        rangeEnd = inputRangeSplits[1];

        done = true;
    }

    // Normally, this function in the RecordReader is called repeatedly to polulate the key and value objects for the mapper.
    // and When the reader gets to the end of the stream, the next method false, and the map task completes.
    // But in our case, it is called only one.

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        /** COMPLETE **/
        // TODO: Write rangeBegin as Key and rangeEnd as Value
        if(done) {
            done = false;
            return true;
        }

        return false;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        if (done) {
            return 1.0f;
        }
        return 0.0f;
    }

    @Override
    public void close() throws IOException {
    }
}
