import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import org.knowm.xchart.SwingWrapper;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;


public class JIT{
	static final int NUM_LISTS = 10000000;


	public static void main(String[] args){
		List<Integer> xValues = new ArrayList<>();
		List<Long> yValues = new ArrayList<>();
		for(int i = 0; i < 500; i++){
			xValues.add(i);
			long start = System.nanoTime();

			for(int j = 0; j < NUM_LISTS; j++){
				List<Integer> L = new LinkedList<>();
			}

			long end = System.nanoTime();
			yValues.add(end - start);
			System.out.println(i + ": " + (end - start));
		}

		 XYChart chart =
                new XYChartBuilder().width(800).height(600).xAxisTitle("Index").yAxisTitle("Time").build();
        chart.addSeries("Time to create lists", xValues, yValues);
        new SwingWrapper(chart).displayChart();
	}
}