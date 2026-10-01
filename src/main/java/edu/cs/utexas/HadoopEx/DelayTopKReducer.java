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

public class DelayTopKReducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{
    private PriorityQueue<AirlineRatio> pq = new PriorityQueue<AirlineRatio>(3);;

//    public void setup(Context context) {
//
//        pq = new PriorityQueue<WordAndCount>(10);
//    }

   public void reduce(Text key, Iterable<FloatWritable> values, Context context)
           throws IOException, InterruptedException {


       for (FloatWritable value : values) {
           pq.add(new AirlineRatio(new Text(key), new FloatWritable(value.get()) ) );
       }

       // keep the priorityQueue size <= heapSize
       while (pq.size() > 3) {
           pq.poll();
       }


   }


    public void cleanup(Context context) throws IOException, InterruptedException {
        List<AirlineRatio> values = new ArrayList<AirlineRatio>(3);

        while (pq.size() > 0) {
            values.add(pq.poll());
        }


        // reverse so they are ordered in descending order
        Collections.reverse(values);


        for (AirlineRatio value : values) {
            context.write(value.getAir(), value.getRatio());
        }


    }
}
