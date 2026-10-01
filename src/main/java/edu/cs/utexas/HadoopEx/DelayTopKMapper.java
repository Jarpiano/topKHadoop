package edu.cs.utexas.HadoopEx;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.PriorityQueue;


import org.apache.log4j.Logger;
public class DelayTopKMapper extends Mapper<Text, Text, Text, FloatWritable> {


	private PriorityQueue<AirlineRatio> pq;

	public void setup(Context context) {
		pq = new PriorityQueue<>();

	}

	public void map(Text key, Text value, Context context)
			throws IOException, InterruptedException {


		float delay = Float.parseFloat(value.toString());

		pq.add(new AirlineRatio(new Text(key), new FloatWritable(delay)) );

		if (pq.size() > 3) {
			pq.poll();
		}
	}

	public void cleanup(Context context) throws IOException, InterruptedException {


		while (pq.size() > 0) {
			AirlineRatio ratio = pq.poll();
			context.write(ratio.getAir(), ratio.getRatio());
		}
	}
}
