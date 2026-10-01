package edu.cs.utexas.HadoopEx;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Iterator;

// im assuming we need to create a new class because the extends signature is diff
public class DelayReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
    public void reduce(Text text, Iterable<FloatWritable> values, Context context)
           throws IOException, InterruptedException {
	    int n = 0;
        float sum = 0;
        
        for (FloatWritable a : values) {
             sum += a.get();
             n++;
        }
 
        if (n > 0) {
            float ratio = sum / n;
            context.write(text, new FloatWritable(ratio));
        }
   }
}
