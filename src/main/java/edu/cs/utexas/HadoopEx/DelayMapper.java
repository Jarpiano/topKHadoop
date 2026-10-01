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

public class DelayMapper extends Mapper<Object, Text, Text, FloatWritable> {
    public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String[] data = value.toString().split(",");
		
        if (data.length > 11 && data[0].startsWith("YEAR") == false) {
            try {
                if (data[11].isEmpty() == false) {
                    float d = Float.parseFloat(data[11]);
                    context.write(new Text(data[4]), new FloatWritable(d));
                }
            } catch (NumberFormatException e) {
                // ignore bad formatting if its there
            }
        }
	}
}
