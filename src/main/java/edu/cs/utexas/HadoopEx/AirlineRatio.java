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

public class AirlineRatio implements Comparable<AirlineRatio>{
    private Text airline;
    private FloatWritable ratio;

    public AirlineRatio(Text a, FloatWritable r) {
        airline = a;
        ratio = r;
    }

    public Text getAir() {
        return airline;
    }

    public FloatWritable getRatio() {
        return ratio;
    }

        /**
     * Compares two sort data objects by their value.
     * @param other
     * @return 0 if equal, negative if this < other, positive if this > other
     */
     @Override
     public int compareTo(AirlineRatio other) {

         return Float.compare(ratio.get(), other.ratio.get());
     }


     public String toString(){

         return "("+airline.toString() +" , "+ ratio.toString()+")";
     }
}